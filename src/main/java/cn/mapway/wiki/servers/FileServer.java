package cn.mapway.wiki.servers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.stereotype.Component;

import com.google.gwt.cell.client.FieldUpdater;

import cn.mapway.wiki.configure.FileUploadProperties;
import cn.mapway.wiki.servlet.FileUploadServlet;

/**
 * 文件服务器.
 * 
 * @author zhangjianshe
 *
 */
@Component
public class FileServer extends ServletRegistrationBean {

	FileUploadProperties properties;

	@Autowired
	public FileServer(FileUploadProperties p) {
		properties = p;
		FileUploadServlet servlet = new FileUploadServlet();
		setServlet(servlet);
		setInitParameters(getInitParameters());
		setLoadOnStartup(1);
		addUrlMappings("/fileserver/*");
	}


	@Override
	public Map<String, String> getInitParameters() {
		Map<String, String> p = new HashMap<String, String>();
		p.put(FileUploadServlet.PARA_PREFIX, properties.getUrlPrefix());
		p.put(FileUploadServlet.PARA_REPOSITORY, properties.getRepository());
		p.put(FileUploadServlet.PARA_SUPPORT_FORMAT, properties.getSupportFormat());
		return p;
	}

}
