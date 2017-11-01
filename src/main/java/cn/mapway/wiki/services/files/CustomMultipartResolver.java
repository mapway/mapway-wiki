package cn.mapway.wiki.services.files;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 自定义的文件上传解析器.
 * 
 * @author zhangjianshe
 *
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

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
		Map<String, RequestFileUploadListener> sessionListener = (Map<String, RequestFileUploadListener>) session
				.getAttribute(SESSION_UPLOAD_INFORMATION);

		if (sessionListener == null) {
			sessionListener = new HashMap<String, RequestFileUploadListener>();
			session.setAttribute(SESSION_UPLOAD_INFORMATION, sessionListener);
		}

		System.out.println(Json.toJson(sessionListener.keySet()));
		return sessionListener;
	}

	/**
	 * 获取Request中对应的上传监听器.
	 * 
	 * @param tag
	 * @return
	 */
	public static RequestFileUploadListener findUploadListener(HttpSession session, String tag) {
		return getSessionListener(session).get(tag);
	}

	@Override
	protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {

		String encoding = determineEncoding(request);
		FileUpload fileUpload = prepareFileUpload(encoding);

		String tag = request.getParameter(REQUEST_TAG);
		if (!Strings.isBlank(tag)) {
			RequestFileUploadListener listener = new RequestFileUploadListener();
			// 加入请求中
			getSessionListener(request.getSession()).put(tag, listener);
			fileUpload.setProgressListener(listener);
		}

		try {
			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
			return parseFileItems(fileItems, encoding);
		} catch (FileUploadBase.SizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
		} catch (FileUploadBase.FileSizeLimitExceededException ex) {
			throw new MaxUploadSizeExceededException(fileUpload.getFileSizeMax(), ex);
		} catch (FileUploadException ex) {
			if (!Strings.isBlank(tag)) {
				// 将上次监听器从Session中移除
				getSessionListener(request.getSession()).remove(tag);
			}
			throw new MultipartException("Failed to parse multipart servlet request", ex);

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
			@SuppressWarnings("unchecked")
			Map<String, RequestFileUploadListener> sessionListener = (Map<String, RequestFileUploadListener>) session
					.getAttribute(SESSION_UPLOAD_INFORMATION);

			if (sessionListener != null) {
				sessionListener.remove(tag);
			}

		}
	}

}