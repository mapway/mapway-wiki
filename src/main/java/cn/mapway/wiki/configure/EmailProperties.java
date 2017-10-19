package cn.mapway.wiki.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮件配置信息
 * @author zhangjianshe
 *
 */
@ConfigurationProperties(prefix="mail")
public class EmailProperties {

	String hostName;
	int smtpPort;
	String userName;
	String password;
	boolean sSLOnConnect;
	String from;
	String fromName;
	String charset;
	String adminEmail;
	
	
	public String getFromName() {
		return fromName;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public boolean issSLOnConnect() {
		return sSLOnConnect;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public void setsSLOnConnect(boolean sSLOnConnect) {
		this.sSLOnConnect = sSLOnConnect;
	}
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	
}
