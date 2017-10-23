package cn.mapway.wiki.api.user;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.main.BaseResp;

@Doc("登录结果")
public class UserLoginResp extends BaseResp{
	@ApiField(value="用户信息")
	public UserInfo user;
}
