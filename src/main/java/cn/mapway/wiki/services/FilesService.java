package cn.mapway.wiki.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import cn.mapway.wiki.api.files.FileUploadResp;
import cn.mapway.wiki.configure.FileUploadProperties;
import cn.mapway.wiki.exception.CodeException;
import cn.mapway.wiki.services.files.RequestFileUploadListener;

/**
 * 文件上传服务类
 * 
 * @author zhangjianshe
 *
 */
@Service
public class FilesService extends BaseService {

	private final static String SUPPORT_FIELS = "SUPPORT_FIELS";

	private static List<String> supportFileFormats;

	private String[] imageTypes;

	@Autowired
	private FileUploadProperties properties;

	public FilesService() {
		imageTypes = Lang.array("png", "jpg", "jpeg", "bmp", "gif");
	}

	/**
	 * 设置文件上传属性.
	 * 
	 * @param p
	 */
	public void setProperties(FileUploadProperties p) {
		properties = p;
	}

	public synchronized List<String> getSupportFormat() {
		if (supportFileFormats == null) {

			supportFileFormats = new ArrayList<String>();
			supportFileFormats.add("png");
			supportFileFormats.add("jpg");
			supportFileFormats.add("gif");
			supportFileFormats.add("jpeg");
			supportFileFormats.add("apk");
			String[] fs = Strings.splitIgnoreBlank(properties.getSupportFormat());

			String envConfigure = System.getenv(SUPPORT_FIELS);
			if (!Strings.isBlank(envConfigure)) {
				String[] fs1 = Strings.splitIgnoreBlank(envConfigure);
				supportFileFormats.addAll(Lang.array2list(fs1));
			}

			supportFileFormats.addAll(Lang.array2list(fs));
		}

		return supportFileFormats;
	}

	/**
	 * 保存用户上传的文件.
	 * 
	 * @param extra
	 * @param relPath2
	 * @param randomName
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws CodeException
	 * @throws IOException
	 */
	public FileUploadResp saveRequestFile(String tag, String extra, String relPath, String randomName, FileItem upfile,
			HttpServletRequest request, HttpServletResponse response) throws IOException {

		FileUploadResp resp = new FileUploadResp();
		if (upfile.getSize() <= 0) {
			resp.fail(1, "没有上传文件信息");
			return resp;
		}

		String filename = upfile.getName();

		// 检查文件格式
		String prefix = filename.substring(filename.lastIndexOf(".") + 1);

		boolean accept = false;
		for (String fp : getSupportFormat()) {
			if (fp.equalsIgnoreCase(prefix)) {
				accept = true;
				break;
			}
		}

		String hashcode = Lang.md5(upfile.getInputStream());

		String fn = "";
		// 保存的文件名
		if (randomName != null && randomName.equals("1")) {
			// 原始文件名
			fn = extractOrigionName(upfile);
		} else {
			// 从hash码中取出前两位构造一个子目录
			String str2 = hashcode.substring(0, 2);
			// 随机文件名
			fn = str2 + File.separator + hashcode + "." + prefix;
		}

		// 相对路径
		relPath = relPath + File.separator + fn;

		// 全路径
		String fullPath = properties.repository + File.separator + relPath;

		// 保存文件
		try {

			Files.write(fullPath, upfile.getInputStream());
			resp.extra = extra;
			resp.md5 = hashcode;
			resp.size = upfile.getSize();
			resp.relPath = relPath.replaceAll("\\\\", "/");
			resp.retCode = 0;
			resp.fileName = extractOrigionName(upfile);
			resp.msg = "success";
			String suffixName = Files.getSuffixName(resp.fileName);
			resp.isPicture = isImage(suffixName);
			resp.icon = suffixName;
		} catch (IOException e) {

		}

		return resp;
	}

	/**
	 * 判断是不是图片.
	 * 
	 * @param fileExtension
	 * @return
	 */
	public boolean isImage(String fileExtension) {
		return Lang.contains(imageTypes, fileExtension);
	}

	/**
	 * 获取原始文件名
	 * 
	 * @param upfile
	 * @return
	 */
	private String extractOrigionName(FileItem upfile) {
		if (upfile == null) {
			return R.UU16();
		}
		String fileName = upfile.getName();
		if (Strings.isBlank(fileName)) {
			return R.UU16();
		}
		String targetName = "";
		String tfn = fileName.replaceAll("\\\\", "/");
		int index = tfn.lastIndexOf('/');
		if (index >= 0) {
			targetName = tfn.substring(tfn.lastIndexOf('/') + 1);
		} else {
			targetName = tfn;
		}
		return targetName;
	}

	private final static String SESSION_UPLOAD_INFORMATION = "SESSION_UPLOAD_INFORMATION";

	public static final String REQUEST_TAG = "tag";

	/**
	 * 获取Session 中的上传监听器,每个Session保存一个
	 * 
	 * @param request
	 * @return
	 */
	public static synchronized Map<String, RequestFileUploadListener> getSessionListener(HttpSession session) {

		@SuppressWarnings("unchecked")
		ConcurrentMap<String, RequestFileUploadListener> sessionListener = (ConcurrentMap<String, RequestFileUploadListener>) session
				.getAttribute(SESSION_UPLOAD_INFORMATION);

		if (sessionListener == null) {
			sessionListener = new ConcurrentHashMap<String, RequestFileUploadListener>();
			sessionListener = new ConcurrentHashMap<>();
			session.setAttribute(SESSION_UPLOAD_INFORMATION, sessionListener);
		}
		return sessionListener;
	}

	/**
	 * 获取Request中对应的上传监听器.
	 * 
	 * @param tag
	 * @return
	 */
	public static RequestFileUploadListener findUploadListener(HttpSession session, String tag) {
		if (!Strings.isBlank(tag)) {
			return getSessionListener(session).get(tag);
		} else {
			return null;
		}
	}

	/**
	 * 移除Session中的监听器.
	 * 
	 * @param session
	 * @param tag
	 */
	public static void removeUploadListener(HttpSession session, String tag) {
		if (!Strings.isBlank(tag)) {
			Map<String, RequestFileUploadListener> sessionListener = getSessionListener(session);
			sessionListener.remove(tag);
		}
	}
}
