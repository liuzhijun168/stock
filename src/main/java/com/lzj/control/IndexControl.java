package com.lzj.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexControl {

	@RequestMapping(value = "")
	public String index(HttpServletRequest request) {
		return "/bootstrap/dangriyingkui";
	}

}