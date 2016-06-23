package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

/**
 * Servlet implementation class ChartServlet
 */
public class ChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			StringBuffer jsonArray = new StringBuffer();
			jsonArray
					.append("{ \"elements\": [ { \"type\": \"bar_filled\", \"colour\": \"#5B56B6\", \"values\": [");
			Map<Integer, Integer> data = ExcelTools.getZhangDieFuData();
			Set<Entry<Integer, Integer>> set = data.entrySet();
			boolean flag = true;
			StringBuffer zhangdiefu = new StringBuffer();
			int maxValue = 0;
			for (Iterator<Entry<Integer, Integer>> iterator = set.iterator(); iterator
					.hasNext();) {
				Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator
						.next();
				int key = entry.getKey();
				int value = entry.getValue();

				if (key < 0) {
					value = value * -1;
				}
				maxValue = Math.max(maxValue, Math.abs(value));
				if (flag) {
					jsonArray.append(value);
					zhangdiefu.append("\"" + key + "\"");
					flag = false;
				} else {
					jsonArray.append("," + value);
					zhangdiefu.append(",\"" + key + "\"");
				}
			}
			maxValue = (maxValue/100+1)*100;
			// jsonArray.append("36.7, 38.7, 42.8, 38.2, 37.8, 34.7, 38.4, 40.2, 39.5, 40.3, 45.9, 48.9, 50.9, 52.9, 57.9, 60.9, 61.9, 76.9, 77.9, 69.9, 77.9, 77.9, 79.9, 88.9, 87.9, 103.9");
			jsonArray.append(" ],\"text\": \"涨跌数量\", \"font-size\": 12 } ],");
			//jsonArray.append("\"title\": {\"text\": \"涨跌幅分布\", \"style\": \"{font-size: 20px; color: #A2ACBA; text-align: center;}\" }, ");
			jsonArray
					.append("\"x_axis\": { \"colour\": \"#A2ACBA\", \"grid-colour\": \"#D7E4A3\",\"labels\": { \"size\":10,\"steps\": 1,\"rotate\": 0, \"colour\": \"#A2ACBA\", ");
			jsonArray.append("\"labels\": [");
			jsonArray.append(zhangdiefu);
			jsonArray.append("] } },");
			jsonArray
					.append("\"x_legend\": { \"text\": \"涨跌幅\", \"style\": \"{font-size: 12px; color: #778877}\" }, ");
			jsonArray
					.append("\"y_axis\": { \"min\": -"+maxValue+", \"max\": "+maxValue+", \"steps\": 80 } }");

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
