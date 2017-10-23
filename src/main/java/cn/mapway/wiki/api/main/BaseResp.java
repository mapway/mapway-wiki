package cn.mapway.wiki.api.main;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("接口返回基类信息")
public class BaseResp {
	@ApiField(value = "返回码 0 正确 非0错误", example = "0")
	public int retCode;

	@ApiField(value = "错误消息", example = "名字长度不能超过64个字符")
	public String msg;

	/**
	 * 设置接口调用成功.
	 * 
	 * @return
	 */
	public BaseResp ok() {
		return ok("");
	}

	/**
	 * 设置接口调用成功.
	 * 
	 * @param message
	 *            成功的消息
	 * @return
	 */
	public BaseResp ok(String message) {
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
	public BaseResp fail(int code, String message) {
		retCode = code;
		this.msg = message;
		return this;
	}
}
