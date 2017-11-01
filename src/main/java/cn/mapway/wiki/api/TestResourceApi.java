package cn.mapway.wiki.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.BaseResp;
import cn.mapway.wiki.api.module.BaseReq;

@RequestMapping(value = "/api/v1/test")
@RestController
@Doc(value="测试",group="/测试")
public class TestResourceApi extends BaseApi {

	/**
	 * 检验管理员权限
	 * 
	 * @param r
	 * @return
	 */
	@RequestMapping(value="/session",method= {RequestMethod.POST})
	@Doc("test session")
	public BaseResp testSession(@RequestBody BaseReq req,HttpServletRequest request) {
		
		BaseResp r = new BaseResp();
		r.ok(request.getSession().getId());
		
		return r;
	}
	
	
	/**
	 * 检验管理员权限
	 * 
	 * @param r
	 * @return
	 */
	@RequestMapping(value="/admin",method= {RequestMethod.POST})
	@RequiresRoles("admin")
	@Doc("管理员权限")
	public BaseResp reqAdmin(@RequestBody BaseReq req) {
		BaseResp r = new BaseResp();
		r.ok("");
		return r;
	}
	
	/**
	 * 检验管理员权限
	 * 
	 * @param r
	 * @return
	 */
	@RequestMapping(value="/test01",method= {RequestMethod.POST})
	@RequiresPermissions(value= {"TEST_01"})
	@Doc("TEST_01权限")
	public BaseResp rest01(@RequestBody BaseReq req) {
		BaseResp r = new BaseResp();
		r.ok("owner test01");
		return r;
	}
}
