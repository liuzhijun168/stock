package com.lzj.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzj.bean.StockDataDay;
import com.lzj.dao.StockDataDayDao;
import com.lzj.util.DateUtil;

@Controller
@RequestMapping("/fz")
public class FuZhuControl {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
	}

	@RequestMapping("/hushenagu_lishi")
	public String lishi(HttpServletRequest request, Date queryDate) {

		if (queryDate == null) {
			queryDate = new Date();
			while (true) {
				queryDate = DateUtil.addDay(queryDate, -1);

				Calendar cal = Calendar.getInstance();
				cal.setTime(queryDate);

				int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
				if (w >= 1 && w <= 5) {
					break;
				}
			}

		}
		StockDataDayDao stockDataDayDao = new StockDataDayDao();
		List<StockDataDay> stockDataDays = stockDataDayDao.getByCreateDate(queryDate);

		request.setAttribute("stockDataDays", stockDataDays);
		// return "redirect:" + "/bootstrap/hushenagu_lishi.jsp";
		return "bootstrap/hushenagu_lishi";
	}
}