package cn.mapway.wiki.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mapway.document.annotation.DevelopmentState;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.AppInfoResp;
import cn.mapway.wiki.api.module.BaseReq;

@RequestMapping(value="/api/v1")
@Controller
@Doc(value="Wiki接口文档",group="/系统",author="张建设" ,state=DevelopmentState.PROCESS)
public class MainApi extends BaseApi{
	
	@RequestMapping(value="/info",method= {RequestMethod.POST})
	@Doc(value="应用程序信息")
	@ResponseBody
	public AppInfoResp info(@RequestBody BaseReq req)
	{
		AppInfoResp r= new AppInfoResp();
		r.appName="测试服务";
		r.ok();
		return r;
	}
}
