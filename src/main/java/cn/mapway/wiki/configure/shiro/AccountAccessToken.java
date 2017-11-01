package cn.mapway.wiki.configure.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 本系统的用户认证模式
 * @author zhangjianshe
 *
 */
public class AccountAccessToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -5948758821599082660L;
	private Integer accountType;
	
	public AccountAccessToken(String userName,String pwd,Integer accountType) {
		super(userName,pwd);
		this.setAccountType(accountType);
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	
}
