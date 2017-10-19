package cn.mapway.wiki.services;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Lang;
import org.nutz.lang.Times;
import org.nutz.lang.hardware.NetworkItem;
import org.nutz.lang.hardware.Networks;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.configure.EmailProperties;
import cn.mapway.wiki.services.EmailService;
import cn.mapway.wiki.tools.Templates;

/**
 * 工具集合.
 * 
 * @author zhangjianshe
 *
 */
@Service
public class Tookits {
	private final static Log log = Logs.getLog(Tookits.class);
	/**
	 * 系统启动发送邮件模板文件
	 */
	private final static String SERVICE_START_FILE = "service-start.html";
	@Autowired
	EmailService emailService;

	@Autowired
	EmailProperties emailProperties;

	/**
	 * 发送系统启动邮件
	 */
	public void sendStartEmail() {

		String title = "服务启动@" + Times.format("yyyyMMdd HH:mm:ss", Times.now());
		emailService.send(emailProperties.getAdminEmail(), title, buildStartMessage());
	}

	private String buildStartMessage() {
		String content = Templates.readTemplate("cn.mapway.wiki.tools.resources", SERVICE_START_FILE);
		Map<String, String> data = new HashMap<String, String>();
		StringBuilder ips = new StringBuilder();
		ips.append("<ul>");
		for (NetworkItem item : Networks.networkItems().values()) {
			if (item.getIpv4() != null) {
				ips.append("<li>" + item.getIpv4() + "</li>");
			}
		}
		ips.append("</ul>");
		data.put("IP", ips.toString());
		data.put("TIME", Times.format("yyyy年MM月dd日 HH:mm:ss", Times.now()));
		content = Templates.replace(content, data);
		return content;
	}

	/**
	 * 打印应用程序信息
	 * @param app
	 */
	public void printApplicationInformation(ConfigurableApplicationContext app) {
		String[] activeProfiles = app.getEnvironment().getActiveProfiles();
	    for (String profile : activeProfiles) {
	    	log.info("current active profile:" + profile);
	    }
	}
}
