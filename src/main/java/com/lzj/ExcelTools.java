package com.lzj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ExcelTools {

	public static void main(String[] args) throws Exception {
		// readExcel();
		getZiJinData();
		// listener();
	}

	public static void listener() throws BiffException, IOException,
			InterruptedException {
		while (true) {
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpGet httpget = new HttpGet(
						"http://hq.sinajs.cn/list=sh600106");
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();

				/*
				 * if (entity != null) {
				 * System.out.println("Response content length: " +
				 * entity.getContentLength()); }
				 */
				// char a = '10';
				System.out.println();
				InputStream inSm = entity.getContent();
				Scanner inScn = new Scanner(inSm);
				while (inScn.hasNextLine()) {
					/*
					 * 0：”大秦铁路”，股票名字； 1：”27.55″，今日开盘价； 2：”27.25″，昨日收盘价；
					 * 3：”26.91″，当前价格； 4：”27.55″，今日最高价； 5：”26.20″，今日最低价；
					 * 6：”26.91″，竞买价，即“买一”报价； 7：”26.92″，竞卖价，即“卖一”报价；
					 * 8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
					 * 9：”589824680
					 * ″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
					 * 10：”4695″，“买一”申请4695股，即47手； 11：”26.91″，“买一”报价；
					 * 12：”57590″，“买二” 13：”26.90″，“买二” 14：”14700″，“买三”
					 * 15：”26.89″，“买三” 16：”14300″，“买四” 17：”26.88″，“买四”
					 * 18：”15100″，“买五” 19：”26.87″，“买五”
					 * 20：”3100″，“卖一”申报3100股，即31手； 21：”26.92″，“卖一”报价 (22, 23),
					 * (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
					 * 30：”2008-01-11″，日期； 31：”15:05:32″，时间；
					 */
					String str = inScn.nextLine()
							.replace("var hq_str_sh600106=", "")
							.replace("\"", "").replace(";", "");
					String[] arrStr = str.split(",");
					int mai1 = Integer.parseInt(arrStr[10]) / 100;
					if (mai1 < 5000) {
						MusicPlay musicPlay = new MusicPlay(new URL(
								"file:///D://Ringin.wav"));
						musicPlay.continuousStart();
					}
				}
				httpget.abort();
				Thread.sleep(1000);

			} finally {
				httpclient.getConnectionManager().shutdown();
			}

		}
	}

	public static Statement getStatement() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/stock?user=root&password=liu168";
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/stock?user=root&password=liu168";
			Connection con = DriverManager.getConnection(url);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void readExcel() throws BiffException, IOException,
			SQLException {

		// 创建一个list 用来存储读取的内容
		List<GuPiaoData> guPiaoDataList = new ArrayList<GuPiaoData>();
		Workbook rwb = null;
		Cell cell = null;

		// 创建输入流
		InputStream stream = new FileInputStream(
				"C:/Users/Administrator/Desktop/Table.xls");

		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);

		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);

		cell = sheet.getCell(0, 0);// code

		Map<String, Integer> map = new HashMap<String, Integer>();
		TreeMap<String, Integer> hangyeMap = new TreeMap<String, Integer>();
		// System.out.println(sheet.getColumns());
		for (int i = 0; i < sheet.getColumns(); i++) {
			map.put(sheet.getCell(i, 0).getContents(), i);
		}
		// {3日换手%=36, 总股本=30, 昨收=18, 3日涨幅%=34, 代码=1, 涨速%=10, 流通市值=33, 量比=3,
		// 6日换手%=37, 涨跌=5, 流通股本=32, 名称=2, 金额=12, 总市值=31, 卖出价=9, 最低=16, 买入价=8,
		// 委比%=21, 卖一量=28, 序=0, 最高=15, 市净率=29, 最新=20, 内盘=24, 市盈率=13, 涨幅%=4,
		// 6日涨幅%=35, 换手%=11, 现手=7, 买一量=27, 外盘=25, 内外比=26, 振幅%=19, 委差=22, 总手=6,
		// 所属行业=14, 均价=23, 开盘=17}

		// 行数(表头的目录不需要，从1开始)
		for (int i = 1; i < sheet.getRows(); i++) {
			GuPiaoData guPiaoData = new GuPiaoData();
			if (i < 100) {
				cell = sheet.getCell(map.get("所属行业"), i);// code
				guPiaoData.setSuoshuhangye(cell.getContents());
				if (hangyeMap.containsKey(guPiaoData.getSuoshuhangye())) {
					int count = hangyeMap.get(guPiaoData.getSuoshuhangye()) + 1;
					hangyeMap.put(guPiaoData.getSuoshuhangye(), count);
				} else {
					hangyeMap.put(guPiaoData.getSuoshuhangye(), 1);
				}
			}

			// 创建一个数组 用来存储每一列的值
			// String[] str = new String[sheet.getColumns()];

			cell = sheet.getCell(map.get("代码"), i);// code
			guPiaoData.setCode(cell.getContents());

			cell = sheet.getCell(map.get("名称"), i);// name
			guPiaoData.setName(cell.getContents());

			cell = sheet.getCell(map.get("最高"), i);// zuigao
			if ("----".equals(cell.getContents())) {
				continue;
			}

			cell = sheet.getCell(map.get("涨幅%"), i);// 涨幅
			guPiaoData.setZhangdiefu(Float.parseFloat(cell.getContents()));

			guPiaoData.setZuigao(Float.parseFloat(cell.getContents()));

			cell = sheet.getCell(map.get("最低"), i);// zuidi
			guPiaoData.setZuidi(Float.parseFloat(cell.getContents()));

			cell = sheet.getCell(map.get("开盘"), i);// kaipan
			guPiaoData.setKaipan(Float.parseFloat(cell.getContents()));

			cell = sheet.getCell(map.get("昨收"), i);// zuoshou
			guPiaoData.setZuoshou(Float.parseFloat(cell.getContents()));

			cell = sheet.getCell(map.get("最新"), i);// zuixin
			guPiaoData.setShoupan(Float.parseFloat(cell.getContents()));

			guPiaoDataList.add(guPiaoData);
			// 把刚获取的列存入list
			// list.add(str);
		}
		TreeMap<Integer, List<GuPiaoData>> guPiaoDataMap = new TreeMap<Integer, List<GuPiaoData>>();
		for (GuPiaoData guPiaoData1 : guPiaoDataList) {
			float kaipanzhangfu = (guPiaoData1.getKaipan() - guPiaoData1
					.getZuoshou()) / guPiaoData1.getZuoshou() * 100;
			float shoupanzhangfu = (guPiaoData1.getShoupan() - guPiaoData1
					.getZuoshou()) / guPiaoData1.getZuoshou() * 100;
			float zuidazhangfu = (guPiaoData1.getZuigao() - guPiaoData1
					.getZuoshou()) / guPiaoData1.getZuoshou() * 100;
			float zuidizhangfu = (guPiaoData1.getZuidi() - guPiaoData1
					.getZuoshou()) / guPiaoData1.getZuoshou() * 100;

			// System.out.println(guPiaoData1.getName()+"   "+Math.floor(shoupanzhangfu));

			int shoupanzhangfuFloor = (int) Math.floor(shoupanzhangfu);

			if (guPiaoDataMap.containsKey(shoupanzhangfuFloor)) {
				List<GuPiaoData> guPiaoDataListTemp = guPiaoDataMap
						.get(shoupanzhangfuFloor);
				guPiaoDataListTemp.add(guPiaoData1);
				guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			} else {
				List<GuPiaoData> guPiaoDataListTemp = new ArrayList<GuPiaoData>();
				guPiaoDataListTemp.add(guPiaoData1);
				guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			}
			/*
			 * if(shoupanzhangfu>2 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */

			/*
			 * if(shoupanzhangfu>1 && (zuidazhangfu-shoupanzhangfu>4)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */
			/*
			 * if(shoupanzhangfu>0 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("2"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */
			/*
			 * if(guPiaoData1.getZuidi()>= guPiaoData1.getKaipan() &&
			 * (shoupanzhangfu+1.5>=zuidazhangfu)){
			 * System.out.println(guPiaoData1
			 * .getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu+",最高涨幅"
			 * +zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */

			/*
			 * if(zuidizhangfu<-3 && shoupanzhangfu > 0 && shoupanzhangfu < 1 ){
			 * System
			 * .out.println(guPiaoData1.getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */
			// System.out.println("Main.readExcel()");
			/*
			 * if(zuidizhangfu<-3 && (kaipanzhangfu<1 && kaipanzhangfu>-1)
			 * shoupanzhangfu <0.5 && shoupanzhangfu > -0.5 ){
			 * System.out.println
			 * (guPiaoData1.getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu
			 * +",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 */

		}
		// System.out.println(guPiaoDataMap);

		List<Map.Entry<String, Integer>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list

		mappingList = new ArrayList<Map.Entry<String, Integer>>(
				hangyeMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1,
							Map.Entry<String, Integer> mapping2) {
						return -mapping1.getValue().compareTo(
								mapping2.getValue());
					}
				});
		System.out.println("行业           数量（涨幅榜前100）");
		for (Map.Entry<String, Integer> mapping : mappingList) {
			System.out.println(mapping.getKey() + ":" + mapping.getValue());
		}

		Set<Entry<Integer, List<GuPiaoData>>> set = guPiaoDataMap.entrySet();
		// set = new TreeSet<Map.Entry<Integer,List<GuPiaoData>>>(set);

		System.out.println("涨幅    数量");
		for (Iterator<Entry<Integer, List<GuPiaoData>>> iterator = set
				.iterator(); iterator.hasNext();) {
			Entry<Integer, List<GuPiaoData>> entry = (Entry<Integer, List<GuPiaoData>>) iterator
					.next();
			System.out
					.println(entry.getKey() + "   " + entry.getValue().size());

		}
	}

	public static void getZiJinData() throws BiffException, IOException,
			SQLException {

		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(
				"C:/Users/Administrator/Desktop/Table.xls");

		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);

		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < sheet.getColumns(); i++) {
			map.put(sheet.getCell(i, 0).getContents(), i);
		}
		// 行数(表头的目录不需要，从1开始)
		for (int i = 1; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				String contents = sheet.getCell(j, i).getContents();
				System.out.print(contents + "   ");
			}
			System.out.println();
		}
	}

	public static Map<String, Integer> getHangYeZiJin(int num)
			throws BiffException, IOException, SQLException {

		String createDate = DBTools
				.getString("select date_format(create_date,'%Y-%m-%d') from stock_zijin sz order by create_date desc limit 0,1");
		
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);

			// 创建一个list 用来存储读取的内容
			List<GuPiaoData> guPiaoDataList = new ArrayList<GuPiaoData>();
			Workbook rwb = null;
			Cell cell = null;

			// 创建输入流
			InputStream stream = new FileInputStream(
					"C:/Users/Administrator/Desktop/Table.xls");

			// 获取Excel文件对象
			rwb = Workbook.getWorkbook(stream);

			// 获取文件的指定工作表 默认的第一个
			Sheet sheet = rwb.getSheet(0);

			cell = sheet.getCell(0, 0);// code

			Map<String, Integer> map = new HashMap<String, Integer>();
			Map<String, Integer> hangyeMap = new HashMap<String, Integer>();
			// System.out.println(sheet.getColumns());
			for (int i = 0; i < sheet.getColumns(); i++) {
				map.put(sheet.getCell(i, 0).getContents(), i);
			}
			String sql = "delete from `stock_zijin` where date_format(create_date,'%Y-%m-%d') =  ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			pstmt.execute();
			sql = "INSERT INTO `stock_zijin`(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					String contents = sheet.getCell(j, i).getContents();
					if (contents.equals("----")) {
						contents = "-99.99";
					}
					/*
					 * System.out.println(j+1 + "===" + sheet.getCell(j,
					 * i).getContents());
					 */
					pstmt.setObject(j + 1, contents);
				}
				pstmt.setObject(sheet.getColumns() + 1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				pstmt.addBatch();
				// preparedStatement.setObject(parameterIndex, x)
			}
			pstmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			try {
				// 提交失败，执行回滚操作
				conn.rollback();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}

		LinkedHashMap<String, Integer> hangyeMap = new LinkedHashMap<String, Integer>();
		

		ResultSet resultSet = DBTools
				.getResult("select * from stock_zijin sz,stock_data sd where date_format(sd.create_date,'%Y-%m-%d') = '2014-12-26' and date_format(sz.create_date,'%Y-%m-%d') = '"
						+ createDate
						+ "' and sd.b = sz.b and sz.d != '----' order by sz.id limit 0,"
						+ num + "");
		while (resultSet.next()) {
			GuPiaoData guPiaoData = new GuPiaoData();
			// code
			guPiaoData.setSuoshuhangye(resultSet.getString("sd.g"));
			if (hangyeMap.containsKey(guPiaoData.getSuoshuhangye())) {
				int count = hangyeMap.get(guPiaoData.getSuoshuhangye()) + 1;
				hangyeMap.put(guPiaoData.getSuoshuhangye(), count);
			} else {
				hangyeMap.put(guPiaoData.getSuoshuhangye(), 1);
			}
		}

		// System.out.println(guPiaoDataMap);

		List<Map.Entry<String, Integer>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list

		mappingList = new ArrayList<Map.Entry<String, Integer>>(
				hangyeMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1,
							Map.Entry<String, Integer> mapping2) {
						return -mapping1.getValue().compareTo(
								mapping2.getValue());
					}
				});
		hangyeMap.clear();
		for (Map.Entry<String, Integer> mapping : mappingList) {
			hangyeMap.put(mapping.getKey(), mapping.getValue());
			if (hangyeMap.size() == 10) {
				break;
			}
			/*
			 * else{ if(hangyeMap.containsKey("其他")){ hangyeMap.put("其他",
			 * hangyeMap.get("其他")+mapping.getValue()); }else{
			 * hangyeMap.put("其他", mapping.getValue()); }
			 * 
			 * }
			 */
		}
		return hangyeMap;
	}

	public static Map<String, Integer> getHangYeData(int num)
			throws BiffException, IOException, SQLException {

		Map<String, Integer> map = new HashMap<String, Integer>();
		LinkedHashMap<String, Integer> hangyeMap = new LinkedHashMap<String, Integer>();
		List<GuPiaoData> guPiaoDataList = new ArrayList<GuPiaoData>();

		ResultSet resultSet = DBTools
				.getResult("select * from stock_data_query sdq,stock_data sd where date_format(sd.create_date,'%Y-%m-%d') = '2014-12-26' and sd.b = sdq.b and sdq.e>-11 order by sdq.e desc limit 0,"
						+ num + "");

		while (resultSet.next()) {
			GuPiaoData guPiaoData = new GuPiaoData();
			// code
			guPiaoData.setCode(resultSet.getString("b"));
			guPiaoData.setName(resultSet.getString("c"));
			guPiaoData.setZuigao(resultSet.getFloat("p"));
			guPiaoData.setZhangdiefu(resultSet.getFloat("e"));
			guPiaoData.setZuidi(resultSet.getFloat("q"));
			guPiaoData.setKaipan(resultSet.getFloat("r"));
			guPiaoData.setZuoshou(resultSet.getFloat("s"));
			guPiaoData.setShoupan(resultSet.getFloat("d"));
			guPiaoData.setSuoshuhangye(resultSet.getString("sd.g"));
			if (hangyeMap.containsKey(guPiaoData.getSuoshuhangye())) {
				int count = hangyeMap.get(guPiaoData.getSuoshuhangye()) + 1;
				hangyeMap.put(guPiaoData.getSuoshuhangye(), count);
			} else {
				hangyeMap.put(guPiaoData.getSuoshuhangye(), 1);
			}
		}

		// System.out.println(guPiaoDataMap);

		List<Map.Entry<String, Integer>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list

		mappingList = new ArrayList<Map.Entry<String, Integer>>(
				hangyeMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1,
							Map.Entry<String, Integer> mapping2) {
						return -mapping1.getValue().compareTo(
								mapping2.getValue());
					}
				});
		hangyeMap.clear();
		for (Map.Entry<String, Integer> mapping : mappingList) {
			hangyeMap.put(mapping.getKey(), mapping.getValue());
			if (hangyeMap.size() == 10) {
				break;
			}
			/*
			 * else{ if(hangyeMap.containsKey("其他")){ hangyeMap.put("其他",
			 * hangyeMap.get("其他")+mapping.getValue()); }else{
			 * hangyeMap.put("其他", mapping.getValue()); }
			 * 
			 * }
			 */
		}
		return hangyeMap;
	}

	// 总股本大于20亿的为大盘，小于5亿的为小盘，而在5亿和20亿之间的为中盘！

	public static Map<String, Integer> getPanZiData(int num)
			throws BiffException, IOException, SQLException {

		Workbook rwb = null;
		Cell cell = null;

		// 创建输入流
		InputStream stream = new FileInputStream(
				"C:/Users/Administrator/Desktop/Table.xls");

		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);

		// 获取文件的指定工作表 默认的第一个
		Sheet sheet = rwb.getSheet(0);

		cell = sheet.getCell(0, 0);// code

		Map<String, Integer> map = new HashMap<String, Integer>();
		LinkedHashMap<String, Integer> panziMap = new LinkedHashMap<String, Integer>();
		panziMap.put("<5", 0);
		panziMap.put("5~10", 0);
		panziMap.put("10~20", 0);
		panziMap.put(">20", 0);
		// System.out.println(sheet.getColumns());
		for (int i = 0; i < sheet.getColumns(); i++) {
			map.put(sheet.getCell(i, 0).getContents(), i);
		}
		// {3日换手%=36, 总股本=30, 昨收=18, 3日涨幅%=34, 代码=1, 涨速%=10, 流通市值=33, 量比=3,
		// 6日换手%=37, 涨跌=5, 流通股本=32, 名称=2, 金额=12, 总市值=31, 卖出价=9, 最低=16, 买入价=8,
		// 委比%=21, 卖一量=28, 序=0, 最高=15, 市净率=29, 最新=20, 内盘=24, 市盈率=13, 涨幅%=4,
		// 6日涨幅%=35, 换手%=11, 现手=7, 买一量=27, 外盘=25, 内外比=26, 振幅%=19, 委差=22, 总手=6,
		// 所属行业=14, 均价=23, 开盘=17}

		// 行数(表头的目录不需要，从1开始)
		for (int i = 1; i < sheet.getRows(); i++) {
			GuPiaoData guPiaoData = new GuPiaoData();
			if (i < num) {
				cell = sheet.getCell(map.get("流通股本"), i);// code

				String liutonggubenStr = cell.getContents();
				float liutonggubenFloat = 0;
				if (liutonggubenStr.contains("万")) {
					liutonggubenFloat = Float.parseFloat(liutonggubenStr
							.replace("万", "")) / 10000;
				} else {
					liutonggubenFloat = Float.parseFloat(liutonggubenStr
							.replace("亿", ""));
				}
				if (liutonggubenFloat <= 5) {
					int count = panziMap.get("<5") + 1;
					panziMap.put("<5", count);
				} else if (liutonggubenFloat <= 10) {
					int count = panziMap.get("5~10") + 1;
					panziMap.put("5~10", count);
				} else if (liutonggubenFloat <= 20) {
					int count = panziMap.get("10~20") + 1;
					panziMap.put("10~20", count);
				} else {
					int count = panziMap.get(">20") + 1;
					panziMap.put(">20", count);
				}
			}
		}

		// System.out.println(guPiaoDataMap);

		List<Map.Entry<String, Integer>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list

		mappingList = new ArrayList<Map.Entry<String, Integer>>(
				panziMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1,
							Map.Entry<String, Integer> mapping2) {
						return -mapping1.getValue().compareTo(
								mapping2.getValue());
					}
				});
		panziMap.clear();
		for (Map.Entry<String, Integer> mapping : mappingList) {
			panziMap.put(mapping.getKey(), mapping.getValue());
			/*
			 * else{ if(hangyeMap.containsKey("其他")){ hangyeMap.put("其他",
			 * hangyeMap.get("其他")+mapping.getValue()); }else{
			 * hangyeMap.put("其他", mapping.getValue()); }
			 * 
			 * }
			 */
		}
		rwb.close();
		stream.close();
		return panziMap;
	}

	public static Map<String, Integer> getBanKuaiData(int num)
			throws BiffException, IOException, SQLException {

		Map<String, Integer> map = new HashMap<String, Integer>();
		LinkedHashMap<String, Integer> hangyeMap = new LinkedHashMap<String, Integer>();
		hangyeMap.put("沪A", 0);
		hangyeMap.put("深A", 0);
		hangyeMap.put("中小板", 0);
		hangyeMap.put("创业板", 0);

		ResultSet resultSet = DBTools
				.getResult("select * from stock_data_query order by e desc limit 0,"
						+ num + "");

		while (resultSet.next()) {
			GuPiaoData guPiaoData = new GuPiaoData();
			// code
			guPiaoData.setCode(resultSet.getString("b"));

			String key = null;
			if (guPiaoData.getCode().startsWith("60")) {
				key = "沪A";
			} else if (guPiaoData.getCode().startsWith("000")) {
				key = "深A";
			} else if (guPiaoData.getCode().startsWith("002")) {
				key = "中小板";
			} else if (guPiaoData.getCode().startsWith("300")) {
				key = "创业板";
			}
			int count = hangyeMap.get(key) + 1;
			hangyeMap.put(key, count);
		}

		List<Map.Entry<String, Integer>> mappingList = null;

		mappingList = new ArrayList<Map.Entry<String, Integer>>(
				hangyeMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<String, Integer>>() {
					public int compare(Map.Entry<String, Integer> mapping1,
							Map.Entry<String, Integer> mapping2) {
						return -mapping1.getValue().compareTo(
								mapping2.getValue());
					}
				});
		hangyeMap.clear();
		for (Map.Entry<String, Integer> mapping : mappingList) {
			hangyeMap.put(mapping.getKey(), mapping.getValue());
		}
		return hangyeMap;
	}

	public static Map<Integer, Integer> getZhangDieFuData()
			throws BiffException, IOException, SQLException {

		// 创建一个list 用来存储读取的内容
		List<GuPiaoData> guPiaoDataList = new ArrayList<GuPiaoData>();

		LinkedHashMap<Integer, Integer> hangyeMap = new LinkedHashMap<Integer, Integer>();

		ResultSet resultSet = DBTools
				.getResult("select * from stock_data_query");

		while (resultSet.next()) {
			GuPiaoData guPiaoData = new GuPiaoData();
			// code
			guPiaoData.setCode(resultSet.getString("b"));
			guPiaoData.setName(resultSet.getString("c"));
			guPiaoData.setZuigao(resultSet.getFloat("p"));
			guPiaoData.setZhangdiefu(resultSet.getFloat("e"));
			guPiaoData.setZuidi(resultSet.getFloat("q"));
			guPiaoData.setKaipan(resultSet.getFloat("r"));
			guPiaoData.setZuoshou(resultSet.getFloat("s"));
			guPiaoData.setShoupan(resultSet.getFloat("d"));
			guPiaoDataList.add(guPiaoData);
		}

		TreeMap<Integer, List<GuPiaoData>> guPiaoDataMap = new TreeMap<Integer, List<GuPiaoData>>();
		for (GuPiaoData guPiaoData1 : guPiaoDataList) {

			float shoupanzhangfu = (guPiaoData1.getShoupan() - guPiaoData1
					.getZuoshou()) / guPiaoData1.getZuoshou() * 100;

			int shoupanzhangfuFloor = (int) Math.floor(shoupanzhangfu);
			if (shoupanzhangfuFloor == -100) {
				shoupanzhangfuFloor = 0;
			}

			if (guPiaoDataMap.containsKey(shoupanzhangfuFloor)) {
				List<GuPiaoData> guPiaoDataListTemp = guPiaoDataMap
						.get(shoupanzhangfuFloor);
				guPiaoDataListTemp.add(guPiaoData1);
				guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			} else {
				List<GuPiaoData> guPiaoDataListTemp = new ArrayList<GuPiaoData>();
				guPiaoDataListTemp.add(guPiaoData1);
				guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			}

		}
		Set<Entry<Integer, List<GuPiaoData>>> set = guPiaoDataMap.entrySet();
		// set = new TreeSet<Map.Entry<Integer,List<GuPiaoData>>>(set);

		for (Iterator<Entry<Integer, List<GuPiaoData>>> iterator = set
				.iterator(); iterator.hasNext();) {
			Entry<Integer, List<GuPiaoData>> entry = (Entry<Integer, List<GuPiaoData>>) iterator
					.next();
			hangyeMap.put(entry.getKey(), entry.getValue().size());
		}

		// System.out.println(guPiaoDataMap);

		List<Map.Entry<Integer, Integer>> mappingList = null;

		// 通过ArrayList构造函数把map.entrySet()转换成list

		mappingList = new ArrayList<Map.Entry<Integer, Integer>>(
				hangyeMap.entrySet());
		// 通过比较器实现比较排序
		Collections.sort(mappingList,
				new Comparator<Map.Entry<Integer, Integer>>() {
					public int compare(Map.Entry<Integer, Integer> mapping1,
							Map.Entry<Integer, Integer> mapping2) {
						return -mapping1.getKey().compareTo(mapping2.getKey());
					}
				});
		hangyeMap.clear();
		for (Map.Entry<Integer, Integer> mapping : mappingList) {
			hangyeMap.put(mapping.getKey(), mapping.getValue());
		}
		return hangyeMap;
	}

}
