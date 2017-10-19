package cn.mapway.wiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import cn.mapway.wiki.configure.EmailProperties;
import cn.mapway.wiki.services.Tookits;

/**
 * Wiki Applicaiton.
 * @author zhangjianshe
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(EmailProperties.class)
public class WikiApplication {

	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(WikiApplication.class, args);
		Tookits tookit=app.getBean(Tookits.class);
		tookit.printApplicationInformation(app);
	}
	
	
}
