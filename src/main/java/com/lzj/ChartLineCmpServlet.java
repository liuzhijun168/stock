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

/**
 * Servlet implementation class ChartServlet
 */
public class ChartLineCmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChartLineCmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			//{ "elements": [ { "type": "line", "values": [ 1, 2, 1, null, null, null, null, null ] } ], "title": { "text": "Sat Mar 07 2015" } }
			StringBuffer jsonArray = new StringBuffer();
			jsonArray
					.append("{ \"elements\": [ ");
			StringBuffer zichan = new StringBuffer();
			zichan.append("{ \"type\": \"line\", \"values\": [");
			
			StringBuffer dapan = new StringBuffer();
			dapan.append("{ \"type\": \"line\", \"values\": [");
			
			List<Report> reports =  DataTools.getReportData();
			boolean flag = true;
			int maxValue = 0;
			int minValue = 0;
			for (int i = 0; i < reports.size(); i++) {
				Report report = reports.get(i);
				maxValue = Math.max(maxValue,(int)report.getSzzsbili_t());
				maxValue = Math.max(maxValue,(int)report.getFudongkuiyinbili_t());
				minValue = Math.min(minValue, (int)report.getSzzsbili_t());
				minValue = Math.min(minValue, (int)report.getFudongkuiyinbili_t());
				if (flag) {
					dapan.append(report.getSzzsbili_t()+"");
					zichan.append(report.getFudongkuiyinbili_t()+"");
					flag = false;
				} else {
					dapan.append("," + report.getSzzsbili_t()+"");
					zichan.append(","+ report.getFudongkuiyinbili_t()+"");
				}
			}
			
			
			zichan.append(" ], \"dot-style\": { \"type\": \"hollow-dot\", \"dot-size\": 4, \"halo-size\": 1, \"colour\": \"#3D5C56\" },\"colour\": \"#3D5C56\"}");
			dapan.append(" ], \"dot-style\": { \"type\": \"hollow-dot\", \"dot-size\": 4, \"halo-size\": 1, \"colour\": \"#C25030\" },\"colour\": \"#C25030\"}");
			jsonArray.append(zichan);
			jsonArray.append(","+dapan);
			jsonArray.append("],\"title\": {\"text\": \"盈亏曲线\" },");
			jsonArray
			.append("\"x_legend\": { \"text\": \"日期\", \"style\": \"{font-size: 12px; color: #778877}\" }, ");
	jsonArray
			.append("\"y_axis\": { \"min\": "+(minValue-2)+", \"max\": "+(maxValue+2)+", \"steps\": 5 } }");
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
