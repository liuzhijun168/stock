package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangYeZiJinChuRuServlet
 */
public class HangYeZiJinChuRuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HangYeZiJinChuRuServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			StringBuffer jsonArray = new StringBuffer();
			jsonArray
					.append("{ \"elements\": [ { \"type\": \"bar_filled\", \"colour\": \"#5B56B6\", \"values\": [");
			int num = Integer.parseInt(request.getParameter("num"));
			Map<String, Integer> data = ExcelTools.getHangYeZiJin(num );
			Set<Entry<String, Integer>> set = data.entrySet();
			boolean flag = true;
			StringBuffer zhangdiefu = new StringBuffer();
			int maxValue = 0;
			for (Iterator<Entry<String, Integer>> iterator = set.iterator(); iterator
					.hasNext();) {
				Entry<String, Integer> entry = (Entry<String, Integer>) iterator
						.next();
				String key = entry.getKey();
				int value = entry.getValue();
				
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
			maxValue = (maxValue/10+1)*10;
			// jsonArray.append("36.7, 38.7, 42.8, 38.2, 37.8, 34.7, 38.4, 40.2, 39.5, 40.3, 45.9, 48.9, 50.9, 52.9, 57.9, 60.9, 61.9, 76.9, 77.9, 69.9, 77.9, 77.9, 79.9, 88.9, 87.9, 103.9");
			jsonArray.append(" ],\"text\": \"数量\", \"font-size\": 12 } ],");
			//jsonArray.append("\"title\": {\"text\": \"涨跌幅分布\", \"style\": \"{font-size: 20px; color: #A2ACBA; text-align: center;}\" }, ");
			jsonArray
					.append("\"x_axis\": { \"colour\": \"#A2ACBA\", \"grid-colour\": \"#D7E4A3\",\"labels\": { \"steps\": 1,\"rotate\": 0, \"colour\": \"#000000\", ");
			jsonArray.append("\"labels\": [");
			jsonArray.append(zhangdiefu);
			jsonArray.append("] } },");
			jsonArray
					.append("\"x_legend\": { \"text\": \"所属行业\", \"style\": \"{font-size: 12px; color: #778877}\" }, ");
			jsonArray
					.append("\"y_axis\": { \"min\": 0, \"max\": "+maxValue+", \"steps\": 4 } }");

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
