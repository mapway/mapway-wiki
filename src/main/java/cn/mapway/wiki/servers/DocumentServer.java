package cn.mapway.wiki.servers;

import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.stereotype.Component;

import cn.mapway.document.servlet.MapwayDocServlet;

/**
 * 对应新版 Document 的Servlet mapway-doc-ui 1.0.83
 * 
 * @author zhangjianshe
 */
@Component
public class DocumentServer extends ServletRegistrationBean {

	/** The log. */
	private static Log LOG = Logs.getLog(DocumentServer.class);

	public DocumentServer() {
		MapwayDocServlet servlet = new MapwayDocServlet();


		Map<String, String> params = getParameters();

		setServlet(servlet);
		addUrlMappings("/doc/*");
		setInitParameters(params);
		setLoadOnStartup(1);
	}

	private Map<String, String> getParameters() {
		Map<String, String> params = new HashMap<String, String>();

		params.put(MapwayDocServlet.PARAM_ANT_HOME, Strings.sNull(System.getenv("ANT_HOME")));
		params.put(MapwayDocServlet.PARAM_AUTHOR, "zhangjsf@enn.com");
		params.put(MapwayDocServlet.PARAM_CONNECTOR_CLASS_NAME, "NeyunApiConnector");
		params.put(MapwayDocServlet.PARAM_CONNECTOR_PACKAGE_NAME, "cn.mapway.wiki.api");
		params.put(MapwayDocServlet.PARAM_DOMAIN, "www.mapway.cn");
		params.put(MapwayDocServlet.PARAM_SCAN_PACKAGES, "cn.mapway.wiki.api");
		params.put(MapwayDocServlet.PARAM_TITLE, "Wiki-接口文档");
		params.put(MapwayDocServlet.PARAM_COPY_RIGHT, "2016-2017 新奥集团");

		return params;
	}
}
