package cn.mapway.wiki.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cn.mapway.wiki.repository.USERObj;
import cn.mapway.wiki.services.AuthService;
import cn.mapway.wiki.services.UserService;

/**
 * 控制器基类
 * 
 * @author zhangjianshe
 *
 */
public class BaseController {

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	/**
	 * 获取当前用户信息
	 * 
	 * @return
	 */
	public USERObj getUser() {
		USERObj user = (USERObj) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
}
