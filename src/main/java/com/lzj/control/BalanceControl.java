package com.lzj.control;

import java.util.Date;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.Balance;
import com.lzj.bean.User;
import com.lzj.dao.BalanceDao;
import com.lzj.dao.BlotterDao;
import com.lzj.util.JsonUtil;

@Controller
@RequestMapping("/balance")
public class BalanceControl {

	@RequestMapping("/addBalance")
	public String blotter(HttpServletRequest request, int changeType, float balance, String remark) {
		
		
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			return "forward:/system/login";  
		}
		
		BalanceDao balanceDao = new BalanceDao();
		
		Balance balanceObj = new Balance();
		balanceObj.setUserId(user.getId());
		balanceObj.setBalance(balance * changeType);
		balanceObj.setRemark(remark);
		balanceObj.setCreateDate(new Date());
		balanceDao.addBalance(balanceObj);
		
		BlotterDao blotterDao = new BlotterDao();
		blotterDao.modifyBlotterBalance(user.getId(), balance * changeType);
		
		return "redirect:/bbtj/dangriyingkui";  
	}
	
	@RequestMapping("/delBlotter")
	public String blotter(HttpServletRequest request, int blotterId) {
		
		BlotterDao blotterDao = new BlotterDao();

		blotterDao.delBlotter(blotterId );
		
		return "forward:/bbtj/dangriyingkui";  
	}
	
	
}