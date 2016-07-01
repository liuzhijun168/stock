package com.lzj.control;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.Balance;
import com.lzj.dao.BalanceDao;
import com.lzj.dao.BlotterDao;

@Controller
@RequestMapping("/balance")
public class BalanceControl {

	@RequestMapping("/addBalance")
	public String blotter(HttpServletRequest request, int changeType, float balance, String remark) {
		
		BalanceDao balanceDao = new BalanceDao();
		
		Balance balanceObj = new Balance();
		balanceObj.setBalance(balance * changeType);
		balanceObj.setRemark(remark);
		balanceObj.setCreateDate(new Date());
		balanceDao.addBalance(balanceObj);
		
		BlotterDao blotterDao = new BlotterDao();
		blotterDao.modifyBlotterBalance(balance * changeType);
		
		return "redirect:"+"/bootstrap/dangriyingkui.jsp";
	}
	
	@RequestMapping("/delBlotter")
	public String blotter(HttpServletRequest request, int blotterId) {
		
		BlotterDao blotterDao = new BlotterDao();

		blotterDao.delBlotter(blotterId );
		
		return "redirect:"+"/bootstrap/dangriyingkui.jsp";
	}
	
	
}