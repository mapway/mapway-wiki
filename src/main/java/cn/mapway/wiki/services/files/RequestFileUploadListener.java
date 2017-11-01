package cn.mapway.wiki.services.files;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.ProgressListener;

/**
 * 每个请求的上传监听器
 * 
 * @author zhangjianshe
 *
 */
public class RequestFileUploadListener implements ProgressListener {

	private Map<Integer, FileUploadState> data;

	public RequestFileUploadListener() {
		data = new HashMap<Integer, FileUploadState>();
	}

	public Map<Integer, FileUploadState> getData() {
		return data;
	}

	/**
	 * Updates the listeners status information.
	 *
	 * @param pBytesRead
	 *            The total number of bytes, which have been read so far.
	 * @param pContentLength
	 *            The total number of bytes, which are being read. May be -1, if
	 *            this number is unknown.
	 * @param pItems
	 *            The number of the field, which is currently being read. (0 = no
	 *            item so far, 1 = first item is being read, ...)
	 */
	@Override
	public synchronized void update(long pBytesRead, long pContentLength, int pItems) {
		if (pItems > 0) {
			FileUploadState state = data.get(pItems);
			if (state == null) {
				state = new FileUploadState();
				data.put(pItems, state);
			}
			state.setByteRead(pBytesRead);
			state.setContentLength(pContentLength);
			state.setItemIndex(pItems);
		}
	}
}