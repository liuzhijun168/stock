package com.lzj.control;

import java.util.Date;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.Blotter;
import com.lzj.bean.User;
import com.lzj.dao.BlotterDao;
import com.lzj.util.JsonUtil;

@Controller
@RequestMapping("/bbtj")
public class BlotterControl {

	@RequestMapping("/addBlotter")
	public String blotter(HttpServletRequest request, float szzs, float balance, float balanceYy,
			Date createDate) {
		
		User user = (User)request.getSession().getAttribute("user");
		
		BlotterDao blotterDao = new BlotterDao();
		Blotter blotter = new Blotter();
		blotter.setUserId(user.getId());
		blotter.setSzzs(szzs);
		blotter.setBalance(balance);
		blotter.setBalanceYy(balanceYy);
		blotter.setCreateDate(createDate);
		blotterDao.addBlotter(blotter );
		System.out.println(JsonUtil.toJosn(user ));
		System.out.println(JsonUtil.toJosn(blotter ));
		return "redirect:/bbtj/dangriyingkui";  
	}
	
	@RequestMapping("/delBlotter")
	public String blotter(HttpServletRequest request, int blotterId) {
		
		BlotterDao blotterDao = new BlotterDao();

		blotterDao.delBlotter(blotterId );
		
		return "redirect:/bbtj/dangriyingkui";  
	}
	
	@RequestMapping("/dangriyingkui")
	public String dangriyingkuiList(HttpServletRequest request) {

		BlotterDao blotterDao = new BlotterDao();

		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		
		if(user == null){
			return "redirect:/system/login";  
		}
		
		int userId = user.getId();
		
		return "/bootstrap/dangriyingkui";  
	}
	
	@RequestMapping("/dangriyingkui_chart")
	public String dangriyingkuiChart(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();
	
		return "/bootstrap/dangriyingkui_chart";
	}
	

	@RequestMapping("/zongzichan_chart")
	public String zongzichanChart(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();

		return "/bootstrap/zongzichan_chart";
	}
	
	@RequestMapping("/dangriyingkui_dapan_chart")
	public String dangriyingkuiDapanChart(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();

		return "/bootstrap/dangriyingkui_dapan_chart";
	}
	
	@RequestMapping("/rijunshouru_chart")
	public String rijunshouruChart(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();

		return "/bootstrap/rijunshouru_chart";
	}
}