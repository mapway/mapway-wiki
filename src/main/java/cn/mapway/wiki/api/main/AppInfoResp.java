package cn.mapway.wiki.api.main;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("应用程序信息")
public class AppInfoResp extends BaseResp {

	@ApiField(value = "服务启动时间", example = "2013-04-05 12:34:21")
	public String startTime;

	@ApiField(value = "应用名称", example = "文档服务器1")
	public String appName;

}
