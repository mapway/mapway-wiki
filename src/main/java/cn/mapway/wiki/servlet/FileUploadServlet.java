package cn.mapway.wiki.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.img.Colors;
import org.nutz.img.Images;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.mapway.document.helper.Scans;
import cn.mapway.wiki.api.files.FileUploadResp;
import cn.mapway.wiki.api.files.FileUploadStateResp;
import cn.mapway.wiki.api.files.UploadResp;
import cn.mapway.wiki.api.main.BaseResp;
import cn.mapway.wiki.configure.FileUploadProperties;
import cn.mapway.wiki.services.FilesService;
import cn.mapway.wiki.services.files.FileUploadState;
import cn.mapway.wiki.services.files.RequestFileUploadListener;

/**
 * 文件上传Servlet.
 * 
 * @author zhangjianshe
 *
 */
public class FileUploadServlet extends HttpServlet {

	public static final Log log = Logs.getLog(FileUploadServlet.class);

	/**
	 * 文件访问前缀
	 */
	public static final String PARA_PREFIX = "PARA_PREFIX";
	/**
	 * 文件存储路径
	 */
	public static final String PARA_REPOSITORY = "PARA_REPOSITORY";
	/**
	 * 文件支持格式
	 */
	public static final String PARA_SUPPORT_FORMAT = "PARA_SUPPORT_FORMAT";

	/**
	 * 文件服务类
	 */
	FilesService fileService;
	/**
	 * 超过此值将文件保存到临时目录中，否则保存在内存中
	 */
	private int SIZE_THRESHOLD = 4 * 1024 * 1204;
	/**
	 * 临时文件夹路径
	 */
	private String TEMP_REPOSITORY = "/tmp";

	private static final long serialVersionUID = 1L;

	/**
	 * 查询上传状态
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		String url = request.getRequestURI();
		if (url.endsWith("/state")) {
			// 查询上传文件状态 需要tag参数
			queryFileUploadState(request, resp);
		} else if (url.endsWith("/icon")) {
			// 查询上传文件状态 需要ext参数
			queryFileIcon(request, resp);
		} else if (url.endsWith("/query")) {
			// 输出文件信息 需要path参数
			queryFileData(request, resp);
		} else if (url.endsWith("/javascript")) {
			// 输出文件信息 Javascript
			queryJavascript(request, resp);
		}else if (url.endsWith("/css")) {
			// 输出文件信息 Javascript
			queryCss(request, resp);
		} else {
			// 输出使用参数信息
			queryUsage(request, resp);
		}

	}

	/**
	 * 查询样式信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void queryCss(HttpServletRequest request, HttpServletResponse response) throws IOException {
		byte[] data = Scans.readBinResource(FileUploadServlet.class.getPackage().getName(), "uploader.css");
		response.setHeader("Content-type", "text/css;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/css");
		Streams.write(response.getOutputStream(), data);
	}

	/**
	 * 輸出Javascript
	 * 
	 * @param request
	 * @param resp
	 * @throws IOException
	 */
	private void queryJavascript(HttpServletRequest request, HttpServletResponse response) throws IOException {
		byte[] data = Scans.readBinResource(FileUploadServlet.class.getPackage().getName(), "uploader.js");
		response.setHeader("Content-type", "text/javascript;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/javascript");
		Streams.write(response.getOutputStream(), data);
	}

	private void queryUsage(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		String usage = "GET 查询信息\r\n";
		usage += "/state?tag=0000 查询文件上传状态\r\n";
		usage += "/icon?ext=bmp 查询文件图标\r\n";
		usage += "/javascript 输出javascript脚本\r\n";
		usage += "/query?path=/rel/path/filename.ext 获取文件数据";
		usage += "POST  上传文件\r\n";
		usage += "?tag=000 tag标识此次上传，由客户端随机生成 ,form编码方式 enctype=multipart/form-data";
		outputError(resp, usage);

	}

	private void queryFileIcon(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		String p = parseQuery(request, "ext", "");
		byte[] data = Scans.readBinResource(FileUploadServlet.class.getPackage().getName() + ".icons", p + ".png");
		Streams.write(resp.getOutputStream(), data);
	}

	private void queryFileData(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		String p = parseQuery(request, "path", "");
		// 输出文件
		try {
			outputFile(request, resp, URLDecoder.decode(p));
		} catch (Exception e) {
			outputError(resp, e.getMessage());
		}
	}

	/**
	 * 输出文件数据
	 * 
	 * @param request
	 * @param resp
	 * @throws IOException
	 */
	private void outputFile(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {

		String fileName = properties.getRepository() + "/" + path;
		log.info("fetch " + fileName);
		String suffixName = Files.getSuffixName(path).toLowerCase();
		response.setContentType(MimeType.get().find(suffixName));
		boolean isImage = fileService.isImage(suffixName);

		File f = new File(fileName);
		if (!f.exists()) {
			outputError(response, "文件不存在");
			return;
		}
		response.setContentLengthLong(f.length());

		if (isImage) {
			String width = request.getParameter("w");
			String height = request.getParameter("h");
			int req_width = 0;
			int req_height = 0;
			if (width != null && width.length() > 0) {
				req_width = Integer.valueOf(width);
			}
			if (height != null && height.length() > 0) {
				req_height = Integer.valueOf(height);
			}
			if (req_width > 0 && req_height > 0) {
				String targetName = Files.getMajorName(path);
				targetName += "_" + req_width + "_" + req_height;
				targetName += "." + suffixName;
				InputStream infile = Files.findFileAsStream(targetName);
				if (infile != null) {
					Streams.writeAndClose(response.getOutputStream(), infile);
				} else {
					Images.zoomScale(fileName, targetName, req_width, req_height, Colors.as("#FFFFFF"));
					Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(targetName));
				}
			} else {
				Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(fileName));
			}
		} else {
			Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(fileName));
		}
	}

	/**
	 * 输出出错消息.
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	private void outputError(HttpServletResponse response, String msg) throws IOException {

		BaseResp r = new BaseResp();
		r.fail(1, msg);

		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		Streams.write(response.getOutputStream(), Json.toJson(r).getBytes(Charset.forName("UTF-8")));

	}

	/**
	 * 查询文件上传状态.
	 * 
	 * @param request
	 * @param resp
	 * @throws IOException
	 */
	private void queryFileUploadState(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		FileUploadStateResp r = new FileUploadStateResp();

		String tag = request.getParameter("tag");
		if (Strings.isBlank(tag)) {
			r.fail(1, "请传入tag参数");
		} else {
			RequestFileUploadListener listener = FilesService.findUploadListener(request.getSession(), tag);

			if (listener == null) {
				r.fail(1, "没有" + tag + "文件上传信息");
			} else {
				Map<Integer, FileUploadState> data = listener.getData();
				boolean finished = true;
				for (FileUploadState s : data.values()) {
					if (!s.isFinished()) {
						finished = false;
					}
					r.files.add(s);
				}
				if (finished) {
					FilesService.removeUploadListener(request.getSession(), tag);
				}
				r.ok();
			}
		}
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		Streams.write(resp.getOutputStream(), Json.toJson(r).getBytes(Charset.forName("UTF-8")));
	}

	/**
	 * 上传文件
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		UploadResp r = new UploadResp();
		File tempRepository = new File(TEMP_REPOSITORY);
		if (!tempRepository.exists()) {
			tempRepository.mkdirs();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory(SIZE_THRESHOLD, tempRepository);

		FileUpload fileUpload = new ServletFileUpload(factory);

		String tag = request.getParameter(FilesService.REQUEST_TAG);
		String extra = parseQuery(request, "extra", "");
		String relPath = parseQuery(request, "relPath", "default");
		String randomName = parseQuery(request, "randomName", "0");

		if (!Strings.isBlank(tag)) {
			RequestFileUploadListener listener = new RequestFileUploadListener();
			// 加入请求中
			FilesService.getSessionListener(request.getSession()).putIfAbsent(tag, listener);
			fileUpload.setProgressListener(listener);
		}

		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);

			for (FileItem item : fileItems) {
				if (!item.isFormField()) {
					// handle file item
					FileUploadResp saveRequestFile = fileService.saveRequestFile(tag, extra, relPath, randomName, item,
							request, resp);

					r.files.add(saveRequestFile);
					if (item instanceof DiskFileItem) {
						DiskFileItem dfi = (DiskFileItem) item;
						File f = dfi.getStoreLocation();
						if (f != null) {
							f.delete();
						}
					}
				}
			}
			r.ok();
		} catch (FileUploadException ex) {
			if (!Strings.isBlank(tag)) {
				// 将上次监听器从Session中移除
				FilesService.removeUploadListener(request.getSession(), tag);
			}
			r.fail(1, ex.getMessage());
		}

		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		Streams.write(resp.getOutputStream(), Lang.ins(Json.toJson(r)));
	}

	FileUploadProperties properties;

	/**
	 * 查询输入参数,并提供缺省返回值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	private String parseQuery(HttpServletRequest request, String key, String defaultValue) {
		if (Strings.isBlank(key)) {
			return defaultValue;
		}

		String v = request.getParameter(key);
		if (v == null) {
			return defaultValue;
		}
		return v;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		System.out.println("servlet init ");
		fileService = new FilesService();

		properties = new FileUploadProperties();
		properties.setUrlPrefix(config.getInitParameter(PARA_PREFIX));
		properties.setSupportFormat(config.getInitParameter(PARA_SUPPORT_FORMAT) == null ? ""
				: config.getInitParameter(PARA_SUPPORT_FORMAT));
		properties.setRepository(config.getInitParameter(PARA_REPOSITORY) == null ? "/repository"
				: config.getInitParameter(PARA_REPOSITORY));
		System.out.println(Json.toJson(properties));
		fileService.setProperties(properties);
	}
}
