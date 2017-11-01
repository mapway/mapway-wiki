package cn.mapway.wiki.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mapway.wiki.repository.ROLEObj;

/**
 * 认证服务.
 * 
 * @author zhangjianshe
 *
 */
@Controller
@RequestMapping(value = "/admin/auth")
public class AuthController extends BaseController {

	@RequestMapping("/index")
	public String index(ModelMap data) {
		List<ROLEObj> roles = authService.listRoles();
		data.put("roles", roles);
		return "admin/auth/index";
	}
}
