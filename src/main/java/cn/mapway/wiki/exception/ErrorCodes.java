package cn.mapway.wiki.exception;

/**
 * 错误编码.
 * 
 * @author zhangjianshe
 */
public class ErrorCodes {

	public static final ErrorCode SUCCESS = new ErrorCode(0, "操作成功");
	public static final ErrorCode ERROR_RESOURCE_NOT_FIND = new ErrorCode(1, "访问的资源不存在");
	public static final ErrorCode ERROR_REQUEST_METHOD_NOT_SUPPORT = new ErrorCode(2, "调用方法不被支持");
	public static final ErrorCode ERROR_REQUEST_BODY = new ErrorCode(3, "接口请求JSON数据格式错误:${0}");
	public static final ErrorCode ERROR_PARAMETER = new ErrorCode(4, "检查输入参数:${0}");
	public static final ErrorCode ERROR_RUNTIME = new ErrorCode(5, "运行时错误");

	public static final ErrorCode ERROR_LOGIN_PAGE = new ErrorCode(1000, "需要登录");
	public static final ErrorCode ERROR_LOGIN_USER_NOT_EXIST = new ErrorCode(1001, "用户不存在${0}");
	public static final ErrorCode ERROR_LOGIN_PASSWORD_NOT_MATCH = new ErrorCode(1002, "密码错误");
	public static final ErrorCode ERROR_LOGIN_CODE = new ErrorCode(1003, "验证码错误");;

}
