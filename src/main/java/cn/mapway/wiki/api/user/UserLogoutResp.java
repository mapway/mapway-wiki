package cn.mapway.wiki.api.user;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.BaseResp;

@Doc("登录结果")
public class UserLogoutResp extends BaseResp{
	@ApiField(value="退出后的返回页面",example="http://ueicloud.cn/")
	public String url;
}
