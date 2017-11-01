package cn.mapway.wiki.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.internal.matchers.Find;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.files.FileUploadResp;
import cn.mapway.wiki.api.files.FileUploadStateResp;
import cn.mapway.wiki.api.files.UploadResp;
import cn.mapway.wiki.exception.CodeException;
import cn.mapway.wiki.services.FilesService;
import cn.mapway.wiki.services.files.CustomMultipartResolver;
import cn.mapway.wiki.services.files.FileUploadState;
import cn.mapway.wiki.services.files.RequestFileUploadListener;

/**
 * 文件上传API
 * 
 * @author zhangjianshe
 *
 */
@Doc(value = "文件服务", group = "/文件服务")
@RequestMapping(value = "/files")
@RestController
public class FilesApi extends BaseApi {

	private static Log log = Logs.getLog(FilesApi.class);
	@Autowired
	FilesService fileService;

	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	@Doc(value = "文件上传", desc = "支持多文件上传,文件上传字段名称为 file")
	public UploadResp upload(
			@ApiField(value = "Session唯一的字符串,用于查询文件上传状态", example = "JUG693034K") @RequestParam(value = "tag", defaultValue = "") String tag,
			@ApiField(value = "额外的参数", example = "123") @RequestParam(value = "extra", defaultValue = "") String extra,
			@ApiField(value = "存储的相对路径", example = "avator") @RequestParam(value = "relPath", defaultValue = "") String relPath,
			@ApiField(value = "是否用随机名字保存文件，1->使用随机名字,0->使用原始名字", example = "1") @RequestParam(value = "randomName", defaultValue = "1") String randomName,
			HttpServletRequest request, HttpServletResponse response)
			throws MultipartException, CodeException, IOException

	{
		log.info("upload tag " + tag);
		UploadResp r = new UploadResp();

		// List<MultipartFile> files = ((MultipartHttpServletRequest)
		// request).getFiles("file");
		// for (MultipartFile file : files) {
		// FileUploadResp r1 = fileService.saveRequestFile(tag, extra, relPath,
		// randomName, file, request, response);
		// r.files.add(r1);
		// }
		r.ok();
		return r;

	}

	/**
	 * 根据TAG获取文件上传状态
	 * 
	 * @param tag
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/state", method = { RequestMethod.GET })
	@Doc(value = "文件上传状态")
	public FileUploadStateResp uploadStatue(@RequestParam(value = "tag", defaultValue = "") String tag,
			HttpServletRequest request) {
		RequestFileUploadListener listener = CustomMultipartResolver.findUploadListener(request.getSession(), tag);

		FileUploadStateResp r = new FileUploadStateResp();
		if (listener == null) {
			r.fail(1, "没有TAG" + tag + "文件上传信息");
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
				CustomMultipartResolver.removeUploadListener(request.getSession(), tag);
			}
			r.ok();
		}
		return r;
	}

}
