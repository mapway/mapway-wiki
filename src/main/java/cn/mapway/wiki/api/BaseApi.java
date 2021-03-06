package cn.mapway.wiki.api;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mapway.wiki.repository.USERObj;
import cn.mapway.wiki.services.UserService;

/**
 * API基类
 * @author zhangjianshe
 *
 */
public class BaseApi {

	/**
	 * 用户服务类
	 */
	@Autowired
	public UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	public USERObj getUser()
	{
		return (USERObj) SecurityUtils.getSubject().getPrincipal();
	}
	
	
	
}
