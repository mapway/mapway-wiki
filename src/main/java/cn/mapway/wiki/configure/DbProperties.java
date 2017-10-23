package cn.mapway.wiki.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 数据源配置信息
 * @author zhangjianshe
 *
 */
@ConfigurationProperties(prefix="db")
public class DbProperties {
	/** The driver. */
	  private String driver = "";

	  /** The url. */
	  private String url = "";

	  /** The user. */
	  private String user = "";

	  /** The password. */
	  private String password;

	  /** The password. */
	  private String schema;

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}
}
