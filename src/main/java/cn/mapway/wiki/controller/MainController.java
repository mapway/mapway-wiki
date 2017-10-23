package cn.mapway.wiki.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.mapway.wiki.api.main.BaseResp;
import cn.mapway.wiki.services.Tookits;

/**
 * 主页面控制器
 * 
 * @author zhangjianshe
 *
 */
@Controller
public class MainController extends BaseController {

	private final static Log log = Logs.getLog(MainController.class);

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String index() {
		return "index";
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login(@RequestParam(value = "url", defaultValue = "") String url, ModelMap data) {
		if(url==null || url.length()==0)
		{
			url="/admin/home";
		}
		data.put("url", url);
		return "login";
	}

	@RequestMapping(value = "/errors/403", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Object error403() {
		BaseResp r = new BaseResp();
		r.fail(403, "资源未被授权");
		return r;
	}

	@RequestMapping(value = "/errors/403")
	public String error403Html() {
		return "errors/403";
	}

	private final static char[] chars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C' };

	@RequestMapping(value = "/captcha", method = { RequestMethod.GET })
	public void captcha(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "w", defaultValue = "0") int w,
			@RequestParam(value = "h", defaultValue = "0") int h) {
		if (w * h < 1) { // 长或宽为0?重置为默认长宽.
			w = 200;
			h = 60;
		}
		Captcha captcha = new Captcha.Builder(w, h).addText(new DefaultTextProducer(4, chars))
				.addBackground(new GradiatedBackgroundProducer()).gimp().build();
		String text = captcha.getAnswer();
		session.setAttribute(Tookits.LOGIN_CODE, text);
		response.setContentType("image/png");
		try {
			ImageIO.write(captcha.getImage(), "png", response.getOutputStream());
		} catch (IOException e) {
			log.error("write image error:" + e.getMessage());
		}
	}

}
