package cn.mapway.wiki.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	@RequiresRoles("admin")
	public String home(ModelMap map) {
		
		map.put("user", getUser());
		return "admin/home";
	}
}
