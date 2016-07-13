package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzj.bean.User;

/**
 * Servlet implementation class ChartServlet
 */
public class ChartLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChartLineServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	/*	HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null){
			request.getRequestDispatcher("/login").forward(request, response);
		}
*/
		int userId = 1;
		try {
			// { "elements": [ { "type": "line", "values": [ 1, 2, 1, null,
			// null, null, null, null ] } ], "title": { "text": "Sat Mar 07
			// 2015" } }
			StringBuffer jsonArray = new StringBuffer();
			jsonArray.append("{ \"elements\": [ { \"type\": \"line\", \"values\": [");
			List<Report> reports = DataTools.getReportData(userId);
			boolean flag = true;
			int maxValue = 0;
			int minValue = 0;
			for (int i = 0; i < reports.size(); i++) {
				Report report = reports.get(i);
				maxValue = Math.max(maxValue, Math.abs((int) report.getFudongkuiyin_y()));
				minValue = Math.min(maxValue, Math.abs((int) report.getFudongkuiyin_y()));
				if (flag) {
					jsonArray.append(report.getFudongkuiyin_y() + "");
					flag = false;
				} else {
					jsonArray.append("," + report.getFudongkuiyin_y() + "");
				}
			}

			if (minValue > 0) {
				minValue = 0;
			}
			int steps = (maxValue - minValue) / 10;
			jsonArray.append(
					" ], \"dot-style\": { \"type\": \"hollow-dot\", \"dot-size\": 4, \"halo-size\": 1, \"colour\": \"#3D5C56\" } } ], \"title\": {\"text\": \"盈亏曲线\" },");
			jsonArray.append("\"x_legend\": { \"text\": \"日期\", \"style\": \"{font-size: 12px; color: #778877}\" }, ");
			jsonArray.append("\"y_axis\": { \"min\": -" + minValue + ", \"max\": " + maxValue + ", \"steps\": " + steps
					+ " } }");
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
