package cn.mapway.wiki.services.files;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

/**
 * 文件上传状态
 * 
 * @author zhangjianshe
 *
 */
@Doc("文件上传状态")
public class FileUploadState {
	/**
	 * 到目前为止读取文件的比特数
	 */
	@ApiField(value = "到目前为止读取文件的字节数", example = "1023")
	private Long byteRead = 0L;

	/**
	 * 文件总大小.
	 */
	@ApiField(value = "文件总大小(字节),可能为-1,表示文件大小未知", example = "30921")
	private Long contentLength = 0L;

	/**
	 * 上传是否完成
	 * 
	 * @return
	 */
	public boolean isFinished() {
		return contentLength < 0 || ((byteRead.equals(contentLength)) && byteRead > 0);
	}

	/**
	 * 目前正在读取第几个文件.
	 */
	@ApiField(value = "目前正在读取第几个文件,从1开始", example = "1")
	private Integer itemIndex;

	public Long getByteRead() {
		return byteRead;
	}

	public void setByteRead(Long byteRead) {
		this.byteRead = byteRead;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public Integer getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}

}