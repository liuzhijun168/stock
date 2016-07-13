package com.lzj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;

import com.lzj.util.HttpUtil;
import com.lzj.util.StockUtil;

public class DataTools {

	private static double initszzs = 2925.23;;

	public static void main(String[] args) {
		// getJinZhenTanDi();
		loadLastestData();
	}

	public static List<String> getStockCodeList() {
		Statement statement = DBTools.getStatement();
		List<String> stockCodeList = new ArrayList<String>();
		try {
			ResultSet resultSet = statement.executeQuery("select b from stock_data_query group by b");
			// ResultSet resultSet = statement.executeQuery("select b from
			// stock_data_query group by b order by create_date ");
			while (resultSet.next()) {
				stockCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockCodeList;

	}

	public static List<String> getProvinceCodeList() {
		Statement statement = DBTools.getStatement();
		List<String> provinceCodeList = new ArrayList<String>();
		try {
			ResultSet resultSet = statement.executeQuery("select id from citys where levelType = 1");
			while (resultSet.next()) {
				provinceCodeList.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provinceCodeList;

	}

	public static double getBenjin() {
		return Double.parseDouble(DBTools.getString("select sum(balance) from balance"));
	}

	public static void loadLastestData() {

		String stockCode = null;
		HttpResponse response = null;
	
		
		//String sysParam = DBTools.getString("select s_value from system_param where s_key='get_net_stock_data'");
		//while ("1".equals(sysParam)) {
			try {
				List<String> stockCodeList = getStockCodeList();
				Collections.shuffle(stockCodeList);
				StringBuffer stockStringBuffer = new StringBuffer();
				for (int i = 0; i < stockCodeList.size(); i++) {
					stockCode = stockCodeList.get(i);

					String fullStockCode = StockUtil.getFullStockCode(stockCode);
					if (i % 100 == 0 || (i == stockCodeList.size() - 1)) {
						stockStringBuffer.append(fullStockCode);
						try {
							String stockString = stockStringBuffer.toString();
							stockStringBuffer = new StringBuffer();
							String url = "http://hq.sinajs.cn/list=" + stockString;
							String str = HttpUtil.getJsonContent(url);
							//System.out.println(url);
							String[] stockDataArr = str.split("\\n");
							for (String stockData : stockDataArr) {
								//System.out.println(stockData);
								int index = str.indexOf("=");
								String stockDataCode = null;
								try{
								    stockDataCode = stockData.substring(index-6,index);
								}catch(Exception e){
									continue;
								}
								stockData = stockData.substring(index+2, stockData.length()-2);
								if (StringUtils.isEmpty(stockData) || StringUtils.isEmpty(stockData.trim())) {
									continue;
								}
								insert(stockDataCode, stockData);
							}
							Thread.sleep(500);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					} else {
						stockStringBuffer.append(fullStockCode).append(",");
					}
				}
				Thread.sleep(150);// 10分钟一个轮回
				//sysParam = DBTools.getString("select s_value from system_param where s_key='get_net_stock_data'");

			} catch (Exception e) {
				e.printStackTrace();
			}

		//}
	}

	private static void insert(String stockCode,String stockDataDetail){
		try{
			PreparedStatement pstmt = null;
			//System.out.println(stockDataCode+"@"+str);
			String[] arrStr = stockDataDetail.split(",");
			// int mai1 = Integer.parseInt(arrStr[10]) / 100;
			String b = stockCode;
			String c = arrStr[0];
			String r = arrStr[1];
			float s = Float.parseFloat(arrStr[2]);
			String d = arrStr[3];
			String p = arrStr[4];
			String q = arrStr[5];
			String o = arrStr[8];
			String m = arrStr[9];
			// e涨幅
			String e = String.format("%.2f", (Float.parseFloat(d) - s) * 100 / s);
			if ("Infinity".equals(e)) {
				e = "-99.99";
			}
	
			String sql = null;
			if ("-99.99".equals(
					DBTools.getString("select b from stock_data_query where b='" + b + "'"))) {
				sql = " INSERT INTO stock_data_query (c,r,p,q,d,o,m,s,e,create_date,b) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			} else {
				sql = " update stock_data_query set c=?,r=?,p=?,q=?,d=?,o=?,m=?,s=?,e=?,create_date=? where b=?";
			}
			pstmt = DBTools.getConn().prepareStatement(sql);
	
			pstmt.setObject(1, c);
			pstmt.setObject(2, r);
			pstmt.setObject(3, p);
			pstmt.setObject(4, q);
			pstmt.setObject(5, d);
			pstmt.setObject(6, o);
			pstmt.setObject(7, m);
			pstmt.setObject(8, s);
			pstmt.setObject(9, e);
			pstmt.setObject(10, arrStr[30] + " " + arrStr[31]);
			pstmt.setObject(11, b);
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<String> getHongSanBing() {
		List<String> hongsanbingList = new ArrayList<String>();
		List<String> strList = DataTools.getStockCodeList();
		for (String stockCode : strList) {
			ResultSet resultSet = DBTools.getResult(
					"select * from stock_data_day  where b='" + stockCode + "'  order by create_date desc limit 0,3");
			int count = 1;
			float open1 = 0;
			float open2 = 0;
			float open3 = 0;
			float close1 = 0;
			float close2 = 0;
			float close3 = 0;
			// e涨幅
			float e1 = 0;
			float e2 = 0;
			float e3 = 0;
			// p最高
			float p1 = 0;
			float p2 = 0;
			float p3 = 0;
			// s昨收
			float s1 = 0;
			float s2 = 0;
			float s3 = 0;
			boolean eFlag = true;
			try {
				while (resultSet.next()) {
					// d最新
					if (count == 1) {
						open3 = resultSet.getFloat("d");
						e3 = resultSet.getFloat("e");
						p3 = resultSet.getFloat("p");
						s3 = resultSet.getFloat("s");
						if (((p3 - s3) / s3 * 100 - e3) > 2) {
							eFlag = false;
						}
					}
					if (count == 2) {
						open2 = resultSet.getFloat("d");
						e2 = resultSet.getFloat("e");
						p2 = resultSet.getFloat("p");
						s2 = resultSet.getFloat("s");
						s2 = resultSet.getFloat("s");
						if (((p2 - s2) / s2 * 100 - e2) > 2) {
							eFlag = false;
						}
					}
					if (count == 3) {
						open1 = resultSet.getFloat("d");
						e1 = resultSet.getFloat("e");
						p1 = resultSet.getFloat("p");
						s1 = resultSet.getFloat("s");
						if (((p1 - s1) / s1 * 100 - e1) > 2) {
							eFlag = false;
						}
					}
					count++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (e1 <= 0 || e2 <= 0 || e3 <= 0 || e3 > 9) {
				continue;
			}
			if (open3 <= open2 || open2 <= open1) {
				continue;
			}
			if (!eFlag) {
				continue;
			}
			if (open2 > open1 && open2 < open1) {
				continue;
			}
			if (open3 > open2 && open3 < open2) {
				continue;
			}
			hongsanbingList.add(stockCode);
		}
		return hongsanbingList;
	}

	public static List<String> getChuanTouPoJiao() {
		List<String> hongsanbingList = new ArrayList<String>();
		List<String> strList = DataTools.getStockCodeList();
		for (String stockCode : strList) {
			ResultSet resultSet = DBTools.getResult(
					"select * from stock_data  where b='" + stockCode + "'  order by create_date desc limit 0,2");
			int count = 1;
			float open1 = 0;
			float open2 = 0;
			float close1 = 0;
			float close2 = 0;
			// e涨幅
			float e1 = 0;
			float e2 = 0;
			// p最高
			float p1 = 0;
			float p2 = 0;
			// s昨收
			float s1 = 0;
			float s2 = 0;
			// q最低
			float q1 = 0;
			float q2 = 0;
			boolean eFlag = true;
			try {
				while (resultSet.next()) {
					// d最新
					if (count == 1) {
						open2 = resultSet.getFloat("d");
						e2 = resultSet.getFloat("e");
						p2 = resultSet.getFloat("p");
						s2 = resultSet.getFloat("s");
						q2 = resultSet.getFloat("q");

					}
					if (count == 2) {
						open1 = resultSet.getFloat("d");
						e1 = resultSet.getFloat("e");
						p1 = resultSet.getFloat("p");
						s1 = resultSet.getFloat("s");
						q1 = resultSet.getFloat("q");

					}
					count++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (e1 > 0 || e2 < 3 || q2 > q1 || p2 < p1) {
				continue;
			}
			hongsanbingList.add(stockCode);
		}
		return hongsanbingList;
	}

	public static List<String> getZaoChenZhiXing() {
		List<String> zaoChenZhiXingList = new ArrayList<String>();
		List<String> strList = DataTools.getStockCodeList();
		for (String stockCode : strList) {
			ResultSet resultSet = DBTools.getResult(
					"select * from stock_data  where b='" + stockCode + "'  order by create_date desc limit 0,3");
			int count = 1;
			float open1 = 0;
			float open2 = 0;
			float open3 = 0;
			float close1 = 0;
			float close2 = 0;
			float close3 = 0;
			// e涨幅
			float e1 = 0;
			float e2 = 0;
			float e3 = 0;
			// p最高
			float p1 = 0;
			float p2 = 0;
			float p3 = 0;
			// s昨收
			float s1 = 0;
			float s2 = 0;
			float s3 = 0;
			boolean eFlag = true;
			// t振幅
			float t2 = 0;
			try {
				while (resultSet.next()) {
					// d最新
					if (count == 1) {
						open3 = resultSet.getFloat("d");
						e3 = resultSet.getFloat("e");
						p3 = resultSet.getFloat("p");
						s3 = resultSet.getFloat("s");
					}
					if (count == 2) {
						open2 = resultSet.getFloat("d");
						e2 = resultSet.getFloat("e");
						p2 = resultSet.getFloat("p");
						s2 = resultSet.getFloat("s");
						t2 = resultSet.getFloat("t");
						if (((p2 - s2) / s2 * 100 - e2) > 1) {
							eFlag = false;
						}
					}
					if (count == 3) {
						open1 = resultSet.getFloat("d");
						e1 = resultSet.getFloat("e");
						p1 = resultSet.getFloat("p");
						s1 = resultSet.getFloat("s");

					}
					count++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (e1 > 0 || e1 > -3 || e2 < 0 || e2 > 1 || e3 < 0) {
				continue;
			}

			if (Math.abs(e3) - Math.abs(e1) < 0) {
				continue;
			}
			/*
			 * if(!eFlag){ continue; }
			 */
			/*
			 * if(open3 <= open2 || open2 <= open1){ continue; } if(!eFlag){
			 * continue; } if(open2 > open1 && open2 < open1 ){ continue; }
			 * if(open3 > open2 && open3 < open2 ){ continue; }
			 */
			zaoChenZhiXingList.add(stockCode);
		}
		return zaoChenZhiXingList;
	}

	public static List<String> getJinZhenTanDi() {
		List<String> hongsanbingList = new ArrayList<String>();
		List<String> strList = DataTools.getStockCodeList();
		for (String stockCode : strList) {
			ResultSet resultSet = DBTools.getResult(
					"select * from stock_data  where b='" + stockCode + "'  order by create_date desc limit 0,3");
			int count = 1;
			float open1 = 0;
			float open2 = 0;
			float open3 = 0;
			float close1 = 0;
			float close2 = 0;
			float close3 = 0;
			// e涨幅
			float e1 = 0;
			float e2 = 0;
			float e3 = 0;
			// p最高
			float p1 = 0;
			float p2 = 0;
			float p3 = 0;
			// s昨收
			float s1 = 0;
			float s2 = 0;
			float s3 = 0;
			// q最低
			float q1 = 0;
			float q2 = 0;
			float q3 = 0;
			boolean eFlag = true;
			try {
				while (resultSet.next()) {
					// d最新
					if (count == 1) {
						open3 = resultSet.getFloat("d");
						e3 = resultSet.getFloat("e");
						q3 = resultSet.getFloat("q");
						p3 = resultSet.getFloat("p");
						s3 = resultSet.getFloat("s");
						if (((p3 - s3) / s3 * 100 - e3) > 2) {
							eFlag = false;
						}
					}
					if (count == 2) {
						open2 = resultSet.getFloat("d");
						e2 = resultSet.getFloat("e");
						s2 = resultSet.getFloat("s");
						q2 = resultSet.getFloat("q");
						q2 = ((q2 - s2) / s2 * 100);
						p2 = resultSet.getFloat("p");
						p2 = ((p2 - s2) / s2 * 100);
					}
					if (count == 3) {
						open1 = resultSet.getFloat("d");
						e1 = resultSet.getFloat("e");
						p1 = resultSet.getFloat("p");
						s1 = resultSet.getFloat("s");
						if (((p1 - s1) / s1 * 100 - e1) > 2) {
							eFlag = false;
						}
					}
					count++;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (q2 > -2 || e2 < 0 || p2 > 1 || e2 > 1 || e3 < 0) {
				continue;
			}
			// 下影线
			// 收盘涨幅，最低跌幅，

			hongsanbingList.add(stockCode);
		}
		return hongsanbingList;
	}

	public static ChiGuDetail getChiGuData(String date) {
		String tempFile = "C:/Users/Administrator/Documents/" + date + " 资金股份查询.txt";
		BufferedReader br;
		ChiGuDetail chiGuDetail = new ChiGuDetail();
		java.io.File file = new java.io.File(tempFile);
		if (file.exists() == false) {
			return null;
		}
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			String line = "";
			String firstLine = br.readLine();
			firstLine = firstLine.replace(".", "");
			Pattern pattern = Pattern.compile("(\\d+)");
			Matcher matcher = pattern.matcher(firstLine);
			String yue = null;
			String keyong = null;
			String cankaoshizhi = null;
			String zichang = null;
			String yinkui = null;
			int count = 0;
			while (matcher.find()) {
				if (count == 0) {
					yue = matcher.group();
				}
				if (count == 1) {
					keyong = matcher.group();
				}
				if (count == 2) {
					cankaoshizhi = matcher.group();
				}
				if (count == 3) {
					zichang = matcher.group();
				}
				if (count == 4) {
					yinkui = matcher.group();
				}
				count++;

			}
			chiGuDetail.setYue(Float.parseFloat(yue) / 100);
			chiGuDetail.setKeyong(Float.parseFloat(keyong) / 100);
			chiGuDetail.setCankaoshizhi(Float.parseFloat(cankaoshizhi) / 100);
			chiGuDetail.setZichang(Float.parseFloat(zichang) / 100);
			chiGuDetail.setYinkui(Float.parseFloat(yinkui) / 100);
			chiGuDetail.setFuzhai(chiGuDetail.getZichang() - chiGuDetail.getKeyong() - chiGuDetail.getCankaoshizhi());
			List<ChiGu> chiguList = new ArrayList<ChiGu>();
			ChiGu chiGu = null;
			br.readLine();
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {
				chiGu = new ChiGu();
				chiGu.setName(line.substring(0, 6));
				line = line.substring(7, line.length());
				String[] chiguArr = line.split("\\s+");
				chiGu.setAmount(Integer.parseInt(chiguArr[1]));
				chiGu.setKemaiAmount(Integer.parseInt(chiguArr[2]));
				chiGu.setChenben(Float.parseFloat(chiguArr[3]));
				chiGu.setFudongyinkui(Float.parseFloat(chiguArr[4]));
				if ("------".equals(chiguArr[5]) || "-100.00".equals(chiguArr[5])) {
					continue;
				}
				if ("------".equals(chiguArr[6]) || "0.00".equals(chiguArr[5])) {
					continue;
				}
				chiGu.setYinkuibili(Float.parseFloat(chiguArr[5]));
				chiGu.setZuixinshizhi(Float.parseFloat(chiguArr[6]));
				chiGu.setDangqianjia(Float.parseFloat(chiguArr[7]));
				chiGu.setJinBuyAmount(Integer.parseInt(chiguArr[8]));
				chiGu.setJinSaleAmount(Integer.parseInt(chiguArr[9]));
				chiGu.setCode(chiguArr[10]);
				chiguList.add(chiGu);
			}
			chiGuDetail.setChiguList(chiguList);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chiGuDetail;
	}

	public static List<Report> getReportData() {
		java.util.List<Report> reports = new ArrayList<Report>();
		double benjin = getBenjin();
		try {

			String query = "select * from blotter order by create_date asc";

			ResultSet rs = DBTools.getResult(query);

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
				int y = Integer.parseInt(report.getCreateDate().substring(0, 4));
				int m = Integer.parseInt(report.getCreateDate().substring(5, 7));
				int d = Integer.parseInt(report.getCreateDate().substring(8, 10));
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
				report.setSzzsbili((szzs - preReport.getSzzs()) * 100 / preReport.getSzzs());
				report.setFudongkuiyin_d(balance - preReport.getChenben());
				report.setFudongkuiyinbili_d(report.getFudongkuiyin_d() * 100 / preReport.getChenben());
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

	public static void setSzzsbili_w(Report report) {
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
		String sql = "select szzs from blotter where create_date between '2000-01-01 00:00:00' and '" + preDate
				+ " 00:00:00' order by create_date desc  limit 0,1";
		double preSzzs = Double.parseDouble(DBTools.getString(sql));
		if (-99.99 == preSzzs) {
			preSzzs = initszzs;
		}
		report.setSzzsbili_w((report.getSzzs() - preSzzs) * 100 / preSzzs);
	}

	public static void setFudongkuiyinbili_w(Report report) {
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

		String sql = "select balance from blotter where create_date between '2000-01-01 00:00:00' and '" + preDate
				+ " 00:00:00' order by create_date desc  limit 0,1";

		double preBalance = Double.parseDouble(DBTools.getString(sql));
		if (-99.99 == preBalance) {
			preBalance = getBenjin();
		}

		report.setFudongkuiyin_w(report.getChenben() - preBalance);
		report.setFudongkuiyinbili_w(report.getFudongkuiyin_w() * 100 / preBalance);
	}

	public static void setFudongkuiyinbili_m(Report report) {
		String y = report.getCreateDate().substring(0, 4);
		String m = report.getCreateDate().substring(5, 7);
		String d = report.getCreateDate().substring(8, 10);
		// String y = report.getCreateDate();
		if ("01".equals(m)) {
			String last = "select balance from blotter where create_date between '" + y + "-" + m
					+ "-01 00:00:00' and '" + y + "-" + m + "-" + d + " 23:59:59' order by create_date desc  limit 0,1";
			double firstDbl = getBenjin();
			double lastDbl = Double.parseDouble(DBTools.getString(last));
			report.setFudongkuiyin_m(lastDbl - firstDbl);
			report.setFudongkuiyinbili_m(report.getFudongkuiyin_m() * 100 / firstDbl);
		} else {
			String pm = (Integer.parseInt(m) - 1) + "";
			String first = "select balance from blotter where create_date between '" + y + "-" + pm
					+ "-01 00:00:00' and '" + y + "-" + pm + "-31 23:59:59' order by create_date desc limit 0,1";
			String last = "select balance from blotter where create_date between '" + y + "-" + m
					+ "-01 00:00:00' and '" + y + "-" + m + "-" + d + " 23:59:59' order by create_date desc  limit 0,1";
			double preBalance = Double.parseDouble(DBTools.getString(first));
			double lastDbl = Double.parseDouble(DBTools.getString(last));
			if (preBalance == -99.99) {
				preBalance = getBenjin();
			}
			report.setFudongkuiyin_m(lastDbl - preBalance);
			report.setFudongkuiyinbili_m(report.getFudongkuiyin_m() * 100 / preBalance);
		}

	}

	public static void setFudongkuiyinbili_y(Report report) {

		String y = report.getCreateDate().substring(0, 4);
		String sql = "select balance from blotter where create_date between '2000-01-01 00:00:00' and '" + y
				+ "-01-01 00:00:00' order by create_date desc  limit 0,1";

		double preBalance = Double.parseDouble(DBTools.getString(sql));
		if (-99.99 == preBalance) {
			preBalance = getBenjin();
		}
		report.setFudongkuiyin_y(report.getChenben() - preBalance);
		report.setFudongkuiyinbili_y(report.getFudongkuiyin_y() * 100 / preBalance);
	}

	public static void setFudongkuiyinbili_t(Report report) {
		report.setFudongkuiyin_t(report.getChenben() - getBenjin());
		report.setFudongkuiyinbili_t(report.getFudongkuiyin_t() * 100 / getBenjin());
	}

	public static String setColor(int p, double num) {
		if (num >= 0) {
			return "<font color='red'>" + String.format("%." + p + "f", num) + "</font>";
		} else if (num < 0) {
			return "<font color='green'>" + String.format("%." + p + "f", num) + "</font>";
		} else {
			return num + "";
		}
	}

	public static String getSystemParam(String key) {
		String sql = "select value from system_param where key = '" + key + "'";
		return DBTools.getString(sql);
	}
}
