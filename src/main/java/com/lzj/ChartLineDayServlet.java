package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lzj.bean.User;

/**
 * Servlet implementation class ChartServlet
 */
public class ChartLineDayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChartLineDayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = (User)request.getSession().getAttribute("user");

			//{ "elements": [ { "type": "line", "values": [ 1, 2, 1, null, null, null, null, null ] } ], "title": { "text": "Sat Mar 07 2015" } }
			StringBuffer jsonArray = new StringBuffer();
			jsonArray
					.append("{ \"elements\": [ { \"type\": \"line\", \"values\": [");
			List<Report> reports =  DataTools.getReportData(user.getId());
			boolean flag = true;
			int maxValue = 0;
			int minValue = 0;
			for (int i = 0; i < reports.size(); i++) {
				Report report = reports.get(i);
				double value = report.getFudongkuiyin_t()/(i+1) ;
				maxValue = Math.max(maxValue, (int)value);
				minValue = Math.min(maxValue, (int)value);
				if (flag) {
					jsonArray.append(value);
					flag = false;
				} else {
					jsonArray.append("," + value);
				}
			}
			
			if(minValue > 0){
				minValue = 0;
			}
			jsonArray.append(" ], \"dot-style\": { \"type\": \"hollow-dot\", \"dot-size\": 4, \"halo-size\": 1, \"colour\": \"#3D5C56\" } } ], \"title\": {\"text\": \"日均盈亏曲线\" },");
			jsonArray
			.append("\"x_legend\": { \"text\": \"日期\", \"style\": \"{font-size: 12px; color: #778877}\" }, ");
	jsonArray
			.append("\"y_axis\": { \"min\": -"+minValue+", \"max\": "+maxValue+", \"steps\": 50 } }");
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.write(jsonArray.toString());
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
