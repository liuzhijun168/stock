package com.lzj.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.Blotter;
import com.lzj.dao.BlotterDao;

@Controller
@RequestMapping("/blotter")
public class BlotterControl {

	@RequestMapping("/addBlotter")
	public String blotter(HttpServletRequest request, float szzs, float balance, float balanceYy,
			Date createDate) {
		
		BlotterDao blotterDao = new BlotterDao();
		
		Blotter blotter = new Blotter();
		blotter.setSzzs(szzs);
		blotter.setBalance(balance);
		blotter.setBalanceYy(balanceYy);
		blotter.setCreateDate(createDate);
		blotterDao.addBlotter(blotter );
		
		return "redirect:"+"/bootstrap/dangriyingkui.jsp";
	}
	
	@RequestMapping("/delBlotter")
	public String blotter(HttpServletRequest request, int blotterId) {
		
		BlotterDao blotterDao = new BlotterDao();

		blotterDao.delBlotter(blotterId );
		
		return "redirect:"+"/bootstrap/dangriyingkui.jsp";
	}
	
	@RequestMapping("/dangriyingkui")
	public String dangriyingkuiList(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();

		return "bootstrap/dangriyingkui";
	}
	
	
}