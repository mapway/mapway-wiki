package cn.mapway.wiki.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mapway.document.annotation.DevelopmentState;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.module.BaseReq;
import cn.mapway.wiki.api.user.UserLoginReq;
import cn.mapway.wiki.api.user.UserLoginResp;
import cn.mapway.wiki.api.user.UserLogoutResp;
import cn.mapway.wiki.exception.CodeException;

@RequestMapping(value="/api/v1/user")
@Controller
@Doc(value="用户相关接口",group="/用户",author="张建设" ,state=DevelopmentState.FINISH)
public class UserApi extends BaseApi{
	
	@RequestMapping(value="/login",method= {RequestMethod.POST})
	@Doc(value="用户登录",state=DevelopmentState.FINISH)
	@ResponseBody
	public UserLoginResp login(@RequestBody @Valid UserLoginReq req,HttpServletRequest request) throws CodeException
	{
		return userService.login(req,request);
	}
	@RequestMapping(value="/logout",method= {RequestMethod.POST})
	@Doc(value="用户退出登录状态",state=DevelopmentState.FINISH)
	@ResponseBody
	public UserLogoutResp logout(@RequestBody @Valid BaseReq req,HttpServletRequest request) throws CodeException
	{
		return userService.logout(req,request);
	}
}
