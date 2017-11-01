package cn.mapway.wiki.api.files;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc(value = "文件上传返回信息")
public class FileUploadResp {

	@ApiField(value = "返回码 0 正确 非0错误", example = "0")
	public Integer retCode;

	@ApiField(value = "错误消息", example = "名字长度不能超过64个字符")
	public String msg;

	/**
	 * 设置接口调用成功.
	 * 
	 * @return
	 */
	public FileUploadResp ok() {
		return ok("");
	}

	/**
	 * 设置接口调用成功.
	 * 
	 * @param message
	 *            成功的消息
	 * @return
	 */
	public FileUploadResp ok(String message) {
		return fail(0, message);
	}

	/**
	 * 设置接口调用错误
	 * 
	 * @param code
	 *            错误代码.
	 * @param message
	 *            错误消息.
	 * @return
	 */
	public FileUploadResp fail(int code, String message) {
		retCode = code;
		this.msg = message;
		return this;
	}

	/** 客户端上传的 extra数据. */
	@ApiField(value = "客户端上传的 extra数据", example = "123")
	public String extra;

	/** 相对路径. */
	@ApiField(value = "图片的相对路径.", example = "/avator/123.png")
	public String relPath;

	/** MD5. */
	@ApiField(value = "文件的HASH值", example = "db2c6088887744bca9bb3293cd4c2a3d")
	public String md5;

	/** 文件的大小(Byte). */
	@ApiField(value = "文件的大小(Byte)", example = "1233213")
	public long size;

	/** 原始文件名. */
	@ApiField(value = "原始文件名", example = "上传的图片")
	public String fileName;

	/** 是否是图片. */
	@ApiField(value = "是否是图片", example = "true")
	public Boolean isPicture;

	@ApiField(value = "文件类型对应的ICON", example = "png.png")
	public String icon;
}
