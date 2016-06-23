package com.lzj;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ReportServlet
 */
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static double benjin;
	private static final double initszzs = 3376.75;

	public ReportServlet() {
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		benjin = Double
				.parseDouble(getString("select sum(balance) from balance"));
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getParameter("m");
		String num = req.getParameter("num");
		List<Report> reports = getReportData();
		if ("addData".equals(method)) {
			String createDate = req.getParameter("createDate");
			String szzs = req.getParameter("szzs");
			String shizhi = req.getParameter("shizhi");
			String zichan = req.getParameter("zichan");
			resp.setContentType("application/json;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();

			try {

				int result = getStatement()
						.executeUpdate(
								"update blotter set szzs="
										+ szzs
										+ ",balance="
										+ zichan
										+ ",balance_yy="
										+ shizhi
										+ "  where date_format(create_date,'%Y-%m-%d') = '"
										+ createDate + "'");
				if (result == 0) {
					System.out.println("insert into blotter(szzs,balance,balance_yy,create_date) values('"
							+ szzs + "','" + zichan + "','" + shizhi
							+ "',str_to_date('" + createDate
							+ " 12:12:12','%Y-%m-%d %H:%i:%s'))");
					getStatement().execute(
							"insert into blotter(szzs,balance,balance_yy,create_date) values('"
									+ szzs + "','" + zichan + "','" + shizhi
									+ "',str_to_date('" + createDate
									+ " 12:12:12','%Y-%m-%d %H:%i:%s'))");
					
				}
				out.write("{success:true}");
				out.flush();
			} catch (SQLException e) {
				e.printStackTrace();
				out.write("{success:false}");
				out.flush();
			}

		} else if ("getData".equals(method)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", true);
			jsonObject.put("totalCount", reports.size());

			List<Report> tempReports = new ArrayList<Report>();
			for (int i = reports.size() - 1; i >= 0; i--) {
				Report report = reports.get(i);
				if (report.isMd()) {
					report.setCreateDate("<font color='blue'>"
							+ report.getCreateDate().substring(2) + "</font>");
				} else {
					report.setCreateDate(report.getCreateDate().substring(2));
				}
				tempReports.add(report);
			}

			jsonObject.put("users", tempReports);
			resp.setContentType("application/json;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(jsonObject.toString());
			out.flush();
		} else if ("uploadData".equals(method)) {

		} else if ("getChartData".equals(method)) {
			String type = req.getParameter("type");

			JSONObject jsonObject = new JSONObject();
			JSONArray array = JSONArray.fromObject(jsonObject);
			StringBuffer jsonStr = new StringBuffer();

			jsonStr.append("{");
			jsonStr.append("'metaData':");
			jsonStr.append("{ ");
			jsonStr.append("'root':'records',");
			jsonStr.append("'fields':[ ");
			jsonStr.append("{'name':'name','type':'string'}, ");
			// jsonStr.append("{'name':'views','type':'string'},");
			jsonStr.append("{'name':'szzs','type':'string'},");
			jsonStr.append("{'name':'visits','type':'string'}");
			jsonStr.append("]");
			jsonStr.append(" },");
			jsonStr.append("'series': ");
			jsonStr.append("[");
			// jsonStr.append("{'type': 'line',displayName: 'Good',yField: 'views'}, ");
			if ("1".equals(type)) {
				jsonStr.append("{'type': 'line',displayName: '盈亏',yField: 'visits'}");
			} else if ("2".equals(type)) {
				jsonStr.append("{'type': 'column',displayName: '资金',yField: 'visits'}");
			} else if ("3".equals(type)) {
				jsonStr.append("{'type': 'line',displayName: '上证指数',yField: 'szzs'},");
				jsonStr.append("{'type': 'line',displayName: '账户',yField: 'visits'}");
			} else if ("4".equals(type)) {
				jsonStr.append("{'type': 'column',displayName: '涨跌幅',yField: 'visits'}");
			} else if ("hangye".equals(type)) {
				jsonStr.append("{'type': 'column',displayName: '所属行业',yField: 'visits'}");
			}else if ("liutongpan".equals(type)) {
				jsonStr.append("{'type': 'column',displayName: '流通股本',yField: 'visits'}");
			}

			jsonStr.append("],  ");
			jsonStr.append("'records':");
			// jsonStr.append(jsonObject.);
			jsonStr.append("[");

			/*
			 * jsonStr.append("{name:'Jul 07', visits: 245000, views: 300000},");
			 * jsonStr
			 * .append("{name:'Aug 07', visits: 240000, views: 350000},");
			 * jsonStr
			 * .append("{name:'Sep 07', visits: 355000, views: 400000},");
			 * jsonStr
			 * .append("{name:'Oct 07', visits: 375000, views: 420000},");
			 * jsonStr
			 * .append("{name:'Nov 07', visits: 490000, views: 450000},");
			 * jsonStr
			 * .append("{name:'Dec 07', visits: 495000, views: 580000},");
			 * jsonStr
			 * .append("{name:'Jan 08', visits: 520000, views: 600000},");
			 * jsonStr.append("{name:'Feb 08', visits: 620000, views: 750000}");
			 */
			
			boolean flag = true;
			if("hangye".equals(type)){
				try {
					Map<String, Integer> data = ExcelTools.getHangYeData(Integer.parseInt(num));
					Set<Entry<String, Integer>> set = data.entrySet();

					for (Iterator<Entry<String, Integer>> iterator = set.iterator(); iterator.hasNext();) {
						Entry<String, Integer> entry = (Entry<String, Integer>) iterator
								.next();
						String key = entry.getKey();
						int value = entry.getValue();
						
						
						if (flag) {
							jsonStr.append("{name:'"
									+ key + "',szzs:'"
									+ value + "', visits: " + value
									+ "}");
							flag = false;
						} else {
							jsonStr.append(",{name:'"
									+ key + "',szzs:'"
									+ value + "', visits: " + value
									+ "}");
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}else if ("liutongpan".equals(type)) {
				Set<Entry<String, Integer>> liutongpanSet = null;
				try {
					liutongpanSet = ExcelTools
							.getPanZiData(Integer.parseInt(num)).entrySet();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (Iterator<Entry<String, Integer>> iterator = liutongpanSet.iterator(); iterator
						.hasNext();) {
					Entry<String, Integer> entry = (Entry<String, Integer>) iterator
							.next();
					String key =  entry.getKey() ;
					int value =  entry.getValue() ;
					if (flag) {
						jsonStr.append("{name:'"
								+ key + "亿',szzs:'"
								+ value + "', visits: " + value
								+ "}");
						flag = false;
					} else {
						jsonStr.append(",{name:'"
								+ key + "亿',szzs:'"
								+ value + "', visits: " + value
								+ "}");
					}
				}

			}else if(!"4".equals(type)){
				for (int i = 0; i < reports.size(); i++) {
					Report report = reports.get(i);
					/*
					 * if(report.isMd()){
					 * report.setCreateDate("<font color='blue'>"+
					 * report.getCreateDate().substring(2)+"</font>"); }else{
					 * report.setCreateDate(report.getCreateDate().substring(2)); }
					 * tempReports.add(report);
					 */
					double data = report.getFudongkuiyin_t();
					if ("1".equals(type)) {
						data = report.getFudongkuiyin_t();
					} else if ("2".equals(type)) {
						data = report.getBenjin();
					} else if ("3".equals(type)) {
						data = report.getFudongkuiyinbili_t();
					}
					if (flag) {
						jsonStr.append("{name:'"
								+ report.getCreateDate().substring(2) + "',szzs:'"
								+ report.getSzzsbili_t() + "', visits: " + data
								+ "}");
						flag = false;
					} else {
						jsonStr.append(",{name:'"
								+ report.getCreateDate().substring(2) + "',szzs:'"
								+ report.getSzzsbili_t() + "', visits: " + data
								+ "}");
					}

				}
			}else{
				try {
					Map<Integer, Integer> data = ExcelTools.getZhangDieFuData();
					Set<Entry<Integer, Integer>> set = data.entrySet();

					for (Iterator<Entry<Integer, Integer>> iterator = set.iterator(); iterator.hasNext();) {
						Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator
								.next();
						int key = entry.getKey();
						int value = entry.getValue();
						
						if(key<0){
							value= value * -1;
						}
						
						if (flag) {
							jsonStr.append("{name:'"
									+ key + "',szzs:'"
									+ value + "', visits: " + value
									+ "}");
							flag = false;
						} else {
							jsonStr.append(",{name:'"
									+ key + "',szzs:'"
									+ value + "', visits: " + value
									+ "}");
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			
			jsonStr.append("]");
			jsonStr.append("}");
			resp.setContentType("application/json;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			// out.write(jsonObject.toString());
			out.write(jsonStr.toString());

			out.flush();
		} else if ("getPieChartData".equals(method)) {

			JSONObject jsonObject = new JSONObject();
			JSONArray array = JSONArray.fromObject(jsonObject);
			StringBuffer jsonStr = new StringBuffer();

			jsonStr.append("{chartview:[");

			try {
				String type = req.getParameter("type");
				if ("chicang".equals(type)) {
					String query = "select * from depot";

					ResultSet rs = getStatement().executeQuery(query);

					boolean flag = true;
					while (rs.next()) {
						// int id = rs.getInt("id");
						String stock_name = rs.getString("stock_name");
						int shizhi = rs.getInt("shizhi");
						if (flag) {
							jsonStr.append("{stock_name:\"" + stock_name
									+ "\",shizhi:" + shizhi + "}");
							flag = false;
						} else {
							jsonStr.append(",{stock_name:\"" + stock_name
									+ "\",shizhi:" + shizhi + "}");
						}

					}
				}else if ("bankuai".equals(type)) {
					Set<Entry<String, Integer>> bankuaiSet = ExcelTools
							.getBanKuaiData(Integer.parseInt(num)).entrySet();

					boolean flag = true;
					for (Iterator<Entry<String, Integer>> iterator = bankuaiSet.iterator(); iterator
							.hasNext();) {
						Entry<String, Integer> entry = (Entry<String, Integer>) iterator
								.next();
						String key =  entry.getKey() ;
						
						if (flag) {
							jsonStr.append("{stock_name:\"" + key
									+ "\",shizhi:" + entry.getValue() + "}");
							flag = false;
						} else {
							jsonStr.append(",{stock_name:\"" + key
									+ "\",shizhi:" + entry.getValue() + "}");
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			jsonStr.append("]}");
			resp.setContentType("application/json;charset=UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();
			// out.write(jsonObject.toString());
			out.write(jsonStr.toString());

			out.flush();
		} else {
			req.setAttribute("reports", reports);
			req.getRequestDispatcher("report.jsp").forward(req, resp);
		}
	}

	public List<Report> getReportData() {
		java.util.List<Report> reports = new ArrayList<Report>();

		try {

			String query = "select * from blotter order by create_date asc";

			ResultSet rs = getStatement().executeQuery(query);

			Report preReport = null;
			while (rs.next()) {
				Report report = new Report();
				int id = rs.getInt("id");
				double szzs = rs.getDouble("szzs");
				double balance = rs.getDouble("balance");
				double balance_yy = rs.getDouble("balance_yy");
				String createDate = rs.getString("create_date");

				report.setId(id);
				report.setCreateDate(createDate.substring(0, 10));
				report.setSzzs(szzs);
				report.setBenjin(benjin);
				report.setChenben(balance);
				report.setCangwei(balance_yy * 100 / balance);

				if (preReport == null) {
					preReport = new Report();
					preReport.setChenben(benjin);
					preReport.setSzzs(initszzs);
				} else {
					report.setBenjin(preReport.getChenben());

				}

				Calendar currCal = Calendar.getInstance();
				int y = Integer
						.parseInt(report.getCreateDate().substring(0, 4));
				int m = Integer
						.parseInt(report.getCreateDate().substring(5, 7));
				int d = Integer.parseInt(report.getCreateDate()
						.substring(8, 10));
				currCal.set(y, m - 1, d);
				currCal.setFirstDayOfWeek(Calendar.MONDAY);
				int day = currCal.get(Calendar.DAY_OF_WEEK);
				currCal.add(Calendar.DATE, currCal.getFirstDayOfWeek() - day);

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String preDate = dateFormat.format(currCal.getTime());
				if (preDate.equals(report.getCreateDate())) {
					report.setMd(true);
				}

				setSzzsbili_w(report);
				report.setSzzsbili_t((szzs - initszzs) * 100 / initszzs);
				report.setSzzsbili((szzs - preReport.getSzzs()) * 100
						/ preReport.getSzzs());
				report.setFudongkuiyin_d(balance - preReport.getChenben());
				report.setFudongkuiyinbili_d(report.getFudongkuiyin_d() * 100
						/ preReport.getChenben());
				setFudongkuiyinbili_w(report);
				setFudongkuiyinbili_m(report);
				setFudongkuiyinbili_y(report);
				setFudongkuiyinbili_t(report);
				reports.add(report);
				preReport = report;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	public void setSzzsbili_w(Report report) {
		Calendar currCal = Calendar.getInstance();
		int y = Integer.parseInt(report.getCreateDate().substring(0, 4));
		int m = Integer.parseInt(report.getCreateDate().substring(5, 7));
		int d = Integer.parseInt(report.getCreateDate().substring(8, 10));
		currCal.set(y, m - 1, d);
		currCal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = currCal.get(Calendar.DAY_OF_WEEK);
		currCal.add(Calendar.DATE, currCal.getFirstDayOfWeek() - day - 1);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String preDate = dateFormat.format(currCal.getTime());
		String sql = "select szzs from blotter where create_date between '2000-01-01 00:00:00' and '"
				+ preDate + " 00:00:00' order by create_date desc  limit 0,1";
		double preSzzs = 0;
		try {
			preSzzs = Double.parseDouble(getString(sql));
		} catch (Exception e) {
			preSzzs = initszzs;
		}
		report.setSzzsbili_w((report.getSzzs() - preSzzs) * 100 / preSzzs);
	}

	public void setFudongkuiyinbili_w(Report report) {
		Calendar currCal = Calendar.getInstance();
		int y = Integer.parseInt(report.getCreateDate().substring(0, 4));
		int m = Integer.parseInt(report.getCreateDate().substring(5, 7));
		int d = Integer.parseInt(report.getCreateDate().substring(8, 10));
		currCal.set(y, m - 1, d);
		currCal.setFirstDayOfWeek(Calendar.MONDAY);
		int day = currCal.get(Calendar.DAY_OF_WEEK);
		currCal.add(Calendar.DATE, currCal.getFirstDayOfWeek() - day - 1);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String preDate = dateFormat.format(currCal.getTime());

		String sql = "select balance from blotter where create_date between '2000-01-01 00:00:00' and '"
				+ preDate + " 00:00:00' order by create_date desc  limit 0,1";

		double preBalance = 0;
		try {
			preBalance = Double.parseDouble(getString(sql));
		} catch (Exception e) {
			preBalance = benjin;
		}
		report.setFudongkuiyin_w(report.getChenben() - preBalance);
		report.setFudongkuiyinbili_w(report.getFudongkuiyin_w() * 100
				/ preBalance);
	}

	public void setFudongkuiyinbili_m(Report report) {

		String m = report.getCreateDate().substring(5, 7);
		// String y = report.getCreateDate();
		String sql = "select balance from blotter where create_date between '2000-01-01 00:00:00' and '2014-"
				+ m + "-01 00:00:00' order by create_date desc  limit 0,1";

		double preBalance = 0;
		try {
			preBalance = Double.parseDouble(getString(sql));
		} catch (Exception e) {
			preBalance = benjin;
		}
		report.setFudongkuiyin_m(report.getChenben() - preBalance);
		report.setFudongkuiyinbili_m(report.getFudongkuiyin_m() * 100
				/ preBalance);
	}

	public void setFudongkuiyinbili_y(Report report) {

		String y = report.getCreateDate().substring(0, 4);
		String sql = "select balance from blotter where create_date between '2000-01-01 00:00:00' and '"
				+ y + "-01-01 00:00:00' order by create_date desc  limit 0,1";

		double preBalance = 0;
		try {
			preBalance = Double.parseDouble(getString(sql));
		} catch (Exception e) {
			preBalance = benjin;
		}
		report.setFudongkuiyin_y(report.getChenben() - preBalance);
		report.setFudongkuiyinbili_y(report.getFudongkuiyin_y() * 100
				/ preBalance);
	}

	public void setFudongkuiyinbili_t(Report report) {

		report.setFudongkuiyin_t(report.getChenben() - benjin);
		report.setFudongkuiyinbili_t(report.getFudongkuiyin_t() * 100 / benjin);
	}

	public Statement getStatement() {

		return DBTools.getStatement();
	}

	public ResultSet getResultSet(String sql) {
		try {
			return getStatement().executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getString(String sql) {
		try {
			ResultSet resultSet = getResultSet(sql);

			while (resultSet.next()) {
				return resultSet.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void main(String[] args) {
System.out.println((715/100+1)*100);
	}
}
