package cn.mapway.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页面控制器
 * 
 * @author zhangjianshe
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	/**
	 * 管理员首页.
	 * 
	 * @return
	 */
	@RequestMapping(value = "home")
	public String home() {
		return "admin/home";
	}
}
