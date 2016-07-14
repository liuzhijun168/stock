package com.lzj.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.User;
import com.lzj.dao.UserDao;

@Controller
@RequestMapping("/system")
public class SystemControl {

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "/login";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "/login";
	}
	
	@RequestMapping(value = "/doLogin")
	public String doLogin(HttpServletRequest request,String loginName,String password) {
		System.out.println(loginName+"    "+password);
		UserDao userDao = new UserDao();
		
		if(loginName != null){
			loginName = loginName.trim();
		}
		
		if(password != null){
			password = password.trim();
		}
		
		User user = userDao.getUser(loginName, password);
		
		if(user != null){
			request.getSession().setAttribute("user", user);
			return "/bootstrap/dangriyingkui";
		}else{
			return "/login";
		}
	}
	
}