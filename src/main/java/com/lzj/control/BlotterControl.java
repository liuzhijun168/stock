package com.lzj.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.Blotter;
import com.lzj.dao.BlotterDao;

@Controller
@RequestMapping("/bbtj")
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
		
		return "forward:/bbtj/dangriyingkui";  
	}
	
	@RequestMapping("/delBlotter")
	public String blotter(HttpServletRequest request, int blotterId) {
		
		BlotterDao blotterDao = new BlotterDao();

		blotterDao.delBlotter(blotterId );
		
		return "forward:/bbtj/dangriyingkui";  
	}
	
	@RequestMapping("/dangriyingkui")
	public String dangriyingkuiList(HttpServletRequest request) {
		
		BlotterDao blotterDao = new BlotterDao();

		return "bootstrap/dangriyingkui";
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