package cn.mapway.wiki.configure.mvc;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import cn.mapway.wiki.exception.CodeException;
import cn.mapway.wiki.exception.CodePageException;
import cn.mapway.wiki.exception.ErrorCodes;

/**
 * 全局控制器 行为定制.
 *
 * @author zhangjianshe
 */

@ControllerAdvice
public class MvcAdvicer {

	private final static Log log = Logs.getLog(MvcAdvicer.class);

	/**
	 * Request handling no handler found.
	 *
	 * @param req
	 *            the req
	 * @param ex
	 *            the ex
	 * @return the model and view
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ModelAndView requestHandlingNoHandlerFound(HttpServletRequest req, NoHandlerFoundException ex) {
		String url = req.getRequestURL().toString();
		return new ErrorView(cn.mapway.wiki.exception.ErrorCodes.ERROR_RESOURCE_NOT_FIND);
	}

	/**
	 * 请求方法找不到.
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NestedServletException.class)
	public ModelAndView requestNoClassDefFoundError(HttpServletRequest req, NoClassDefFoundError ex) {
		String url = req.getRequestURL().toString();

		return new ErrorView(ErrorCodes.ERROR_RESOURCE_NOT_FIND.code, ErrorCodes.ERROR_RESOURCE_NOT_FIND.message);
	}

	/**
	 * 调用方法不支持时 返回的视图.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ModelAndView handlerMethodNotSupportException(HttpServletRequest request, Exception exception) {
		String url = request.getRequestURL().toString();
		;
		return new ErrorView(ErrorCodes.ERROR_REQUEST_METHOD_NOT_SUPPORT);
	}

	/**
	 * Http message not readable exception handler.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ModelAndView httpMessageNotReadableExceptionHandler(HttpServletRequest request,
			HttpMessageNotReadableException exception) {
		String url = request.getRequestURL().toString();

		return new ErrorView(ErrorCodes.ERROR_REQUEST_BODY.bind(exception.getMessage()));
	}

	/**
	 * Code exception error handler.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(value = { CodeException.class })
	public ModelAndView codeExceptionErrorHandler(HttpServletRequest request, CodeException exception) {
		String url = request.getRequestURL().toString();

		if (isRequestJson(request)) {
			// 输出JSon格式
			return new ErrorView(exception.getCode(), exception.getMessage());

		} else {
			if (exception.getCode() == ErrorCodes.ERROR_RESOURCE_UNAUTHENTICATED.code) {
				ModelMap mm = new ModelMap();
				ModelAndView mav;
				mm.put("data", exception.getMessage());
				mm.put("url", url);
				mav = new ModelAndView("login", mm);
				return mav;
			} else if (exception.getCode() == ErrorCodes.ERROR_RESOURCE_UNAUTHORIZED.code) {
				ModelMap mm = new ModelMap();
				ModelAndView mav;
				mm.put("data", exception.getMessage());
				mm.put("url", url);
				mav = new ModelAndView("errors/403", mm);
				return mav;
			} else {
				ModelMap mm = new ModelMap();
				ModelAndView mav;
				mm.put("data", exception.getMessage());
				mm.put("url", url);
				mav = new ModelAndView("errors/runtime", mm);
				return mav;
			}
		}
	}

	/**
	 * Code exception error handler.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(value = { CodePageException.class })
	public ModelAndView codePageExceptionErrorHandler(HttpServletRequest request, CodePageException exception) {
		ModelMap mm = new ModelMap();
		ModelAndView mav;
		if (exception.getCode() == ErrorCodes.ERROR_LOGIN_PAGE.code) {
			mm.put("data", exception.getMessage());
			mm.put("url", request.getRequestURL());
			mav = new ModelAndView("login", mm);
		} else {
			mm.put("data", exception.getMessage());

			mav = new ModelAndView("login", mm);
		}
		return mav;
	}

	/**
	 * 处理参数绑定验证错误.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ModelAndView handleBindError(HttpServletRequest request, MethodArgumentNotValidException exception) {
		BindingResult br = exception.getBindingResult();

		StringBuilder sb = new StringBuilder();
		for (ObjectError ex : br.getAllErrors()) {
			sb.append(ex.getDefaultMessage());
			sb.append(";");
		}
		String url = request.getRequestURL().toString();
		return new ErrorView(ErrorCodes.ERROR_PARAMETER.bind(sb.toString()));
	}

	/**
	 * Default error handler.
	 *
	 * @param request
	 *            the request
	 * @param exception
	 *            the exception
	 * @return the model and view
	 */
	@ExceptionHandler(value = { Exception.class })
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception exception) {

		String msg = exception.toString();
		if (msg.contains("NullPointerException")) {
			msg = "空指针错误";
		}
		StackTraceElement[] stackTrace = exception.getStackTrace();
		if (stackTrace.length > 0) {
			StackTraceElement ste = stackTrace[0];
			msg += "\r\n错误代码在" + ste.getClassName() + "类:" + ste.getMethodName() + "方法的" + ste.getLineNumber() + "行";
		}
		return new ErrorView(5, msg);
	}

	/**
	 * 未认证异常
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { UnauthenticatedException.class })
	public ModelAndView UnauthenticatedExceptionHandler(HttpServletRequest request, UnauthenticatedException e) {

		String url = request.getRequestURL().toString();
		if (isRequestJson(request)) {

			return new ErrorView(ErrorCodes.ERROR_RESOURCE_UNAUTHENTICATED.bind(url + e.getMessage()));
		} else {
			ModelMap mm = new ModelMap();
			ModelAndView mav;
			mm.put("data", e.getMessage());
			mm.put("url", url);
			mav = new ModelAndView("login", mm);
			return mav;
		}
	}

	/**
	 * 未授权
	 * 
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = { UnauthorizedException.class })

	public ModelAndView UnauthorizedExceptionHandler(HttpServletRequest request, UnauthorizedException e) {
		String url = request.getRequestURL().toString();
		if (isRequestJson(request)) {

			return new ErrorView(ErrorCodes.ERROR_RESOURCE_UNAUTHORIZED.bind(url + e.getMessage()));
		} else {
			ModelMap mm = new ModelMap();
			ModelAndView mav;
			mm.put("data", e.getMessage());
			mm.put("url", url);
			mav = new ModelAndView("login", mm);
			return mav;
		}

	}

	/**
	 * 答应请求header信息.
	 * 
	 * @param request
	 */
	private void printRequestHeader(HttpServletRequest request) {
		Enumeration<String> as = request.getAttributeNames();

		while (as.hasMoreElements()) {
			String s = as.nextElement();
			log.info(s + ":" + request.getAttribute(s));
		}
	}

	/**
	 * 判断请求是不是JSON格式
	 * 
	 * @param req
	 * @return
	 */
	private boolean isRequestJson(HttpServletRequest req) {
		String contentType = req.getContentType();
		if (Strings.isBlank(contentType)) {
			return false;
		}

		return contentType.contains("json");
	}
}
