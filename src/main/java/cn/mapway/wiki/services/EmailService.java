package cn.mapway.wiki.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.nutz.lang.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mapway.wiki.configure.EmailProperties;

/**
 * 邮件发送服务
 * 
 * @author zhangjianshe
 *
 */
@Service
public class EmailService {

	private final static Logger log = LoggerFactory.getLogger(EmailService.class);

	
	private EmailProperties emailProperties;

	/**
	 * 任务执行器
	 */
	private ExecutorService executor;

	/**
	 * 系统启动发哦送你个邮件通知
	 */
	public EmailService(@Autowired EmailProperties p) {
		setEmailProperties(p);
		executor = Executors.newSingleThreadExecutor();
	}

	/**
	 * 发送邮件
	 * 
	 * @param mailAddress
	 * @param title
	 * @param content
	 */
	public final void send(String mailAddress, String title, String content) {

		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					HtmlEmail sender = getEmail();
					sender.setSubject(title);
					sender.setMsg(content);
					sender.addTo(mailAddress);
					sender.buildMimeMessage();
					sender.sendMimeMessage();
					log.info("send email %s with %s successed", mailAddress, title);

				} catch (Exception e) {
					log.info("send email fail", e);

				}

			}
		});

	}

	/**
	 * 构造发送Email对象
	 * @return
	 * @throws EmailException
	 */
	protected HtmlEmail getEmail() throws EmailException {
		HtmlEmail email=new HtmlEmail();
		email.setAuthentication(getEmailProperties().getUserName(), getEmailProperties().getPassword());
		email.setHostName(getEmailProperties().getHostName());
		email.setSmtpPort(getEmailProperties().getSmtpPort());
		email.setFrom(getEmailProperties().getFrom(), getEmailProperties().getFromName());
		email.setSSLOnConnect(getEmailProperties().issSLOnConnect());
		email.setCharset(getEmailProperties().getCharset());
		return email;
	}

	public EmailProperties getEmailProperties() {
		return emailProperties;
	}

	public void setEmailProperties(EmailProperties emailProperties) {
		this.emailProperties = emailProperties;
	}
}
