package cn.mapway.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/files")
@Controller
public class FileController extends BaseController {

	@RequestMapping(value = "/upload")
	public String upload() {
		return "files/upload"; 
	}
}
