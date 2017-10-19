package cn.mapway.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主页面控制器
 * @author zhangjianshe
 *
 */
@Controller
public class MainController extends BaseController{
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value="/",method= {RequestMethod.GET})
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/json")
	@ResponseBody
	public Object json()
	{
		return "{touch:123}";
	}
}
