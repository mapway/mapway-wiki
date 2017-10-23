package cn.mapway.wiki.api.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.mapway.document.annotation.ApiField;
import cn.mapway.document.annotation.Code;
import cn.mapway.document.annotation.Codes;
import cn.mapway.document.annotation.Doc;
import cn.mapway.wiki.api.module.BaseReq;

@Doc("用户登录")
public class UserLoginReq extends BaseReq{

	@ApiField(value="用户登录名",example="admin")
	@Length(min=3,max=64,message="用户名需要3-64个字符")
	@NotNull(message="输入用户名")
	public String userName;

	@ApiField(value="密码",example="12345678")
	@Length(min=6,max=64,message="密码6-64个字符")
	@NotNull(message="输入m密码")
	public String pwd;
	 

	@Codes({ @Code(value="0",desc="注册账号") ,@Code(value="1",desc="集团账号")})
	@ApiField(value="账户类型",example="0")
	public Integer accountType=0;
	

	@ApiField(value="验证码",example="3248")
	public String code="";
}
