package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HangYeLongHuBanServlet
 */
public class BanKuaiLongHuBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BanKuaiLongHuBanServlet() {
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
					.append("{ \"elements\": [ { \"type\": \"pie\", \"alpha\": 0.6, \"start-angle\": 35, \"animate\": [ { \"type\": \"fade\" } ], \"tip\": \"#val# of #total# #percent# of 100%\", \"colours\": [ \"#1C9E05\", \"#FF368D\" ], \"values\": [");
			int num = Integer.parseInt(request.getParameter("num"));
			Map<String, Integer> data = ExcelTools.getBanKuaiData(num );
			Set<Entry<String, Integer>> set = data.entrySet();
			boolean flag = true;
			for (Iterator<Entry<String, Integer>> iterator = set.iterator(); iterator
					.hasNext();) {
				Entry<String, Integer> entry = (Entry<String, Integer>) iterator
						.next();
				String key = entry.getKey();
				int value = entry.getValue();
				if (flag) {
					jsonArray.append("{ \"value\": "+value+", \"label\": \""+key+"\" }");
					flag = false;
				} else {
					 
					jsonArray.append(",{ \"value\": "+value+", \"label\": \""+key+"\" }");
				}
			}
			jsonArray.append("] } ], \"title\": { \"text\": \"板块分布\" }, \"x_axis\": null }");
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
