package cn.mapway.wiki.api.user;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Doc;

@Doc("用户信息")
public class UserInfo {
	@ApiField(value="用户名",example="zjs")
	public String name;
	@ApiField(value="创建时间",example="2017-10-24 14:00:00")
	public String createTime;
	@ApiField(value="更新时间",example="2017-10-24 14:00:00")
	public String updateTime;
	@ApiField(value="用户TOKEN,有效期为用户在此设备再次登录，或者退出",example="23")
	public String token;
	@ApiField(value="头像",example="http://img.ennwifi.cn/avator/123.png")
	public String avator;
}
