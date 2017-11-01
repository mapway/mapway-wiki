package cn.mapway.wiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

import cn.mapway.wiki.configure.DbProperties;
import cn.mapway.wiki.configure.EmailProperties;
import cn.mapway.wiki.configure.FileUploadProperties;
import cn.mapway.wiki.services.Tookits;
import cn.mapway.wiki.services.files.CustomMultipartResolver;

/**
 * Wiki Applicaiton.
 * 
 * @author zhangjianshe
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({ EmailProperties.class, DbProperties.class, FileUploadProperties.class })
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
public class WikiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(WikiApplication.class, args);
		Tookits tookit = app.getBean(Tookits.class);
		tookit.printApplicationInformation(app);
		tookit.initDatabase();
	}
}
