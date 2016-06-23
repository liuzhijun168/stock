package com.lzj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {

	public static void main(String[] args) throws Exception {
		String currentDate = "2015-03-22";
		// readExcel1();
	    //readExcel1(currentDate);
		//loadTodayData(currentDate);
		 //readZiJinExcel(currentDate);
		//listener();
		 //MaTools.copyField();
		// MaTools.updateMa();
		// MaTools.copyMa(currentDate);
		//DataTools.loadLastestData();
		MaTools.updateBBI();
		// C:\Users\Administrator\Documents\20150113 资金股份查询.txt
		//String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		//System.out.println(DataTools.getChiGuData(date));
		/*
		List<String> strings = DataTools.getStockCodeList();
		int count = 0;
		for (int i = 0; i < strings.size(); i++) {
			String code = strings.get(i);
			String r = DBTools.getString("select b from (select * from stock_data where b = '"+code+"' order by create_date desc limit 0,10) sd where sd.ac=0 and sd.e>9 order by create_date desc");
			if("-99.99".equals(r)){
				continue;
			}
			count++;
		}
		System.out.println(count);*/
		
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

	public static void loadTodayData(String date) throws BiffException,
			IOException, SQLException {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();

			// 创建一个list 用来存储读取的内容
			List<GuPiaoData> guPiaoDataList = new ArrayList<GuPiaoData>();
			Workbook rwb = null;
			Cell cell = null;

			// 创建输入流
			InputStream stream = new FileInputStream(
					"C:/Users/Administrator/Desktop/每日数据/" + date + ".xls");

			// 获取Excel文件对象
			rwb = Workbook.getWorkbook(stream);

			// 获取文件的指定工作表 默认的第一个
			Sheet sheet = rwb.getSheet(0);

			cell = sheet.getCell(0, 0);// code

			Map<String, Integer> map = new HashMap<String, Integer>();
			// System.out.println(sheet.getColumns());
			for (int i = 0; i < sheet.getColumns(); i++) {
				map.put(sheet.getCell(i, 0).getContents(), i);
			}
			// {3日换手%=36, 总股本=30, 昨收=18, 3日涨幅%=34, 代码=1, 涨速%=10, 流通市值=33, 量比=3,
			// 6日换手%=37, 涨跌=5, 流通股本=32, 名称=2, 金额=12, 总市值=31, 卖出价=9, 最低=16,
			// 买入价=8, 委比%=21, 卖一量=28, 序=0, 最高=15, 市净率=29, 最新=20, 内盘=24, 市盈率=13,
			// 涨幅%=4, 6日涨幅%=35, 换手%=11, 现手=7, 买一量=27, 外盘=25, 内外比=26, 振幅%=19,
			// 委差=22, 总手=6, 所属行业=14, 均价=23, 开盘=17}

			// 行数(表头的目录不需要，从1开始)
			// for (int i = 1; i < sheet.getRows(); i++) {
			// String date = new SimpleDateFormat("yyyy-MM-dd").format(new
			// Date());
			/*
			 * String sql =
			 * "delete from `stock_data` where date_format(create_date,'%Y-%m-%d') =  ?"
			 * ; pstmt = conn.prepareStatement(sql); pstmt.setObject(1, date);
			 * pstmt.execute();
			 */
			String sql = "INSERT INTO `stock_data_day`(b,c,r,p,q,d,o,m,s,e,m5,m10,m20,m30,m60,m120,m250,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,0,0,0,0,0,0,0,?)";

			for (int i = 1; i < sheet.getRows(); i++) {
				pstmt = DBTools.getConn().prepareStatement(sql);
				pstmt.setObject(1, sheet.getCell(map.get("代码"), i)
						.getContents());
				pstmt.setObject(2, sheet.getCell(map.get("名称"), i)
						.getContents());
				pstmt.setObject(3, sheet.getCell(map.get("开盘"), i)
						.getContents().replace("----", "-99.99"));
				pstmt.setObject(4, sheet.getCell(map.get("最高"), i)
						.getContents().replace("----", "-99.99"));
				pstmt.setObject(5, sheet.getCell(map.get("最低"), i)
						.getContents().replace("----", "-99.99"));
				pstmt.setObject(6, sheet.getCell(map.get("最新"), i)
						.getContents().replace("----", "-99.99"));

				String zongshou = sheet.getCell(map.get("总手"), i).getContents()
						.replace("----", "-99.99");
				;
				if (zongshou.contains("万")) {
					zongshou = Float.parseFloat(zongshou.replace("万", ""))
							* 10000 + "";
				} else {
					zongshou = Float.parseFloat(zongshou.replace("亿", ""))
							* 10000 * 10000 + "";
					;
				}
				pstmt.setObject(7, zongshou);

				String jine = sheet.getCell(map.get("金额"), i).getContents()
						.replace("----", "-99.99");
				;
				if (jine.contains("万")) {
					jine = Float.parseFloat(jine.replace("万", "")) * 10000 + "";
				} else {
					jine = Float.parseFloat(jine.replace("亿", "")) * 10000
							* 10000 + "";
					;
				}
				pstmt.setObject(7, zongshou);

				pstmt.setObject(8, jine);
				pstmt.setObject(9, sheet.getCell(map.get("昨收"), i)
						.getContents().replace("----", "-99.99"));
				pstmt.setObject(10, sheet.getCell(map.get("涨幅%"), i)
						.getContents().replace("----", "-99.99"));
				pstmt.setObject(11, date);
				pstmt.execute();
				System.out.println(i);

				// preparedStatement.setObject(parameterIndex, x)
			}
			// pstmt.executeBatch();
			/*
			 * GuPiaoData guPiaoData = new GuPiaoData(); if(i < 100){ cell =
			 * sheet.getCell(map.get("所属行业"), i);//code
			 * guPiaoData.setSuoshuhangye(cell.getContents());
			 * if(hangyeMap.containsKey(guPiaoData.getSuoshuhangye())){ int
			 * count = hangyeMap.get(guPiaoData.getSuoshuhangye()) +1 ;
			 * hangyeMap.put(guPiaoData.getSuoshuhangye(), count); }else{
			 * hangyeMap.put(guPiaoData.getSuoshuhangye(), 1); } }
			 * 
			 * 
			 * // 创建一个数组 用来存储每一列的值 String[] str = new
			 * String[sheet.getColumns()];
			 * 
			 * cell = sheet.getCell(map.get("代码"), i);//code
			 * guPiaoData.setCode(cell.getContents());
			 * 
			 * cell = sheet.getCell(map.get("名称"), i);//name
			 * guPiaoData.setName(cell.getContents());
			 * 
			 * 
			 * 
			 * cell = sheet.getCell(map.get("最高"), i);//zuigao
			 * if("----".equals(cell.getContents())){ continue; }
			 * 
			 * cell = sheet.getCell(map.get("涨幅%"), i);//涨幅
			 * guPiaoData.setZhangdiefu(Float.parseFloat(cell.getContents()));
			 * 
			 * guPiaoData.setZuigao(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("最低"), i);//zuidi
			 * guPiaoData.setZuidi(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("开盘"), i);//kaipan
			 * guPiaoData.setKaipan(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("昨收"), i);//zuoshou
			 * guPiaoData.setZuoshou(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("最新"), i);//zuixin
			 * guPiaoData.setShoupan(Float.parseFloat(cell.getContents()));
			 * 
			 * guPiaoDataList.add(guPiaoData); // 把刚获取的列存入list //list.add(str);
			 * } Map<Integer, List<GuPiaoData>> guPiaoDataMap = new
			 * HashMap<Integer, List<GuPiaoData>>(); for (GuPiaoData guPiaoData1
			 * : guPiaoDataList) { float kaipanzhangfu =
			 * (guPiaoData1.getKaipan()
			 * -guPiaoData1.getZuoshou())/guPiaoData1.getZuoshou()*100; float
			 * shoupanzhangfu =
			 * (guPiaoData1.getShoupan()-guPiaoData1.getZuoshou(
			 * ))/guPiaoData1.getZuoshou()*100; float zuidazhangfu =
			 * (guPiaoData1
			 * .getZuigao()-guPiaoData1.getZuoshou())/guPiaoData1.getZuoshou
			 * ()*100; float zuidizhangfu =
			 * (guPiaoData1.getZuidi()-guPiaoData1.getZuoshou
			 * ())/guPiaoData1.getZuoshou()*100;
			 * 
			 * 
			 * 
			 * //System.out.println(guPiaoData1.getName()+"   "+Math.floor(
			 * shoupanzhangfu));
			 * 
			 * int shoupanzhangfuFloor = (int)Math.floor(shoupanzhangfu) ;
			 * 
			 * if(guPiaoDataMap.containsKey(shoupanzhangfuFloor)){
			 * List<GuPiaoData> guPiaoDataListTemp =
			 * guPiaoDataMap.get(shoupanzhangfuFloor);
			 * guPiaoDataListTemp.add(guPiaoData1);
			 * guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			 * }else{ List<GuPiaoData> guPiaoDataListTemp = new
			 * ArrayList<GuPiaoData>(); guPiaoDataListTemp.add(guPiaoData1);
			 * guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp); }
			 * if(shoupanzhangfu>2 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * 
			 * if(shoupanzhangfu>1 && (zuidazhangfu-shoupanzhangfu>4)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * if(shoupanzhangfu>0 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("2"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * if(guPiaoData1.getZuidi()>= guPiaoData1.getKaipan() &&
			 * (shoupanzhangfu+1.5>=zuidazhangfu)){
			 * System.out.println(guPiaoData1
			 * .getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu+",最高涨幅"
			 * +zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * if(zuidizhangfu<-3 && shoupanzhangfu > 0 && shoupanzhangfu < 1 ){
			 * System
			 * .out.println(guPiaoData1.getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * //System.out.println("Main.readExcel()"); if(zuidizhangfu<-3 &&
			 * (kaipanzhangfu<1 && kaipanzhangfu>-1) shoupanzhangfu <0.5 &&
			 * shoupanzhangfu > -0.5 ){
			 * System.out.println(guPiaoData1.getName()+
			 * "("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu+",最高涨幅"+zuidazhangfu
			 * +",最低涨幅:"+zuidizhangfu); }
			 * 
			 * }
			 */
			// conn.commit();
			// conn.setAutoCommit(true);
		} catch (SQLException ex) {
			/*
			 * try { // 提交失败，执行回滚操作 conn.rollback();
			 * 
			 * } catch (SQLException e) { e.printStackTrace();
			 * System.err.println("updateExistsInfo回滚执行失败!!!"); }
			 */

			ex.printStackTrace();
			System.err.println("updateExistsInfo执行失败");

		} 
		// System.out.println(guPiaoDataMap);

		/*
		 * Set<Entry<String,Integer>> hangyeSet = hangyeMap.entrySet();
		 * 
		 * 
		 * for (Iterator iterator = hangyeSet.iterator(); iterator.hasNext();) {
		 * Entry<String,Integer> entry = (Entry<String,Integer>) iterator
		 * .next(); System.out.println(entry.getKey()+"   "+entry.getValue());
		 * 
		 * }
		 * 
		 * Set<Entry<Integer, List<GuPiaoData>>> set = guPiaoDataMap.entrySet();
		 * 
		 * for (Iterator iterator = set.iterator(); iterator.hasNext();) {
		 * Entry<Integer, List<GuPiaoData>> entry = (Entry<Integer,
		 * List<GuPiaoData>>) iterator .next();
		 * System.out.println(entry.getKey()+"   "+entry.getValue().size());
		 * 
		 * }
		 */
	}

	public static void readExcel1(String date) throws BiffException,
			IOException, SQLException {
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
					"C:/Users/liuzhijun/Desktop/Table.xls");

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
			// {3日换手%=36, 总股本=30, 昨收=18, 3日涨幅%=34, 代码=1, 涨速%=10, 流通市值=33, 量比=3,
			// 6日换手%=37, 涨跌=5, 流通股本=32, 名称=2, 金额=12, 总市值=31, 卖出价=9, 最低=16,
			// 买入价=8, 委比%=21, 卖一量=28, 序=0, 最高=15, 市净率=29, 最新=20, 内盘=24, 市盈率=13,
			// 涨幅%=4, 6日涨幅%=35, 换手%=11, 现手=7, 买一量=27, 外盘=25, 内外比=26, 振幅%=19,
			// 委差=22, 总手=6, 所属行业=14, 均价=23, 开盘=17}

			// 行数(表头的目录不需要，从1开始)
			// for (int i = 1; i < sheet.getRows(); i++) {
			// String date = new SimpleDateFormat("yyyy-MM-dd").format(new
			// Date());
			String sql = "delete from `stock_data` where date_format(create_date,'%Y-%m-%d') =  ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, date);
			pstmt.execute();
			sql = "INSERT INTO `stock_data`(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,ab,ac,ad,ae,af,ag,ah,ai,aj,ak,al,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				pstmt.setObject(sheet.getColumns() + 1, date);
				pstmt.addBatch();
				// preparedStatement.setObject(parameterIndex, x)
			}
			pstmt.executeBatch();
			/*
			 * GuPiaoData guPiaoData = new GuPiaoData(); if(i < 100){ cell =
			 * sheet.getCell(map.get("所属行业"), i);//code
			 * guPiaoData.setSuoshuhangye(cell.getContents());
			 * if(hangyeMap.containsKey(guPiaoData.getSuoshuhangye())){ int
			 * count = hangyeMap.get(guPiaoData.getSuoshuhangye()) +1 ;
			 * hangyeMap.put(guPiaoData.getSuoshuhangye(), count); }else{
			 * hangyeMap.put(guPiaoData.getSuoshuhangye(), 1); } }
			 * 
			 * 
			 * // 创建一个数组 用来存储每一列的值 String[] str = new
			 * String[sheet.getColumns()];
			 * 
			 * cell = sheet.getCell(map.get("代码"), i);//code
			 * guPiaoData.setCode(cell.getContents());
			 * 
			 * cell = sheet.getCell(map.get("名称"), i);//name
			 * guPiaoData.setName(cell.getContents());
			 * 
			 * 
			 * 
			 * cell = sheet.getCell(map.get("最高"), i);//zuigao
			 * if("----".equals(cell.getContents())){ continue; }
			 * 
			 * cell = sheet.getCell(map.get("涨幅%"), i);//涨幅
			 * guPiaoData.setZhangdiefu(Float.parseFloat(cell.getContents()));
			 * 
			 * guPiaoData.setZuigao(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("最低"), i);//zuidi
			 * guPiaoData.setZuidi(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("开盘"), i);//kaipan
			 * guPiaoData.setKaipan(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("昨收"), i);//zuoshou
			 * guPiaoData.setZuoshou(Float.parseFloat(cell.getContents()));
			 * 
			 * cell = sheet.getCell(map.get("最新"), i);//zuixin
			 * guPiaoData.setShoupan(Float.parseFloat(cell.getContents()));
			 * 
			 * guPiaoDataList.add(guPiaoData); // 把刚获取的列存入list //list.add(str);
			 * } Map<Integer, List<GuPiaoData>> guPiaoDataMap = new
			 * HashMap<Integer, List<GuPiaoData>>(); for (GuPiaoData guPiaoData1
			 * : guPiaoDataList) { float kaipanzhangfu =
			 * (guPiaoData1.getKaipan()
			 * -guPiaoData1.getZuoshou())/guPiaoData1.getZuoshou()*100; float
			 * shoupanzhangfu =
			 * (guPiaoData1.getShoupan()-guPiaoData1.getZuoshou(
			 * ))/guPiaoData1.getZuoshou()*100; float zuidazhangfu =
			 * (guPiaoData1
			 * .getZuigao()-guPiaoData1.getZuoshou())/guPiaoData1.getZuoshou
			 * ()*100; float zuidizhangfu =
			 * (guPiaoData1.getZuidi()-guPiaoData1.getZuoshou
			 * ())/guPiaoData1.getZuoshou()*100;
			 * 
			 * 
			 * 
			 * //System.out.println(guPiaoData1.getName()+"   "+Math.floor(
			 * shoupanzhangfu));
			 * 
			 * int shoupanzhangfuFloor = (int)Math.floor(shoupanzhangfu) ;
			 * 
			 * if(guPiaoDataMap.containsKey(shoupanzhangfuFloor)){
			 * List<GuPiaoData> guPiaoDataListTemp =
			 * guPiaoDataMap.get(shoupanzhangfuFloor);
			 * guPiaoDataListTemp.add(guPiaoData1);
			 * guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp);
			 * }else{ List<GuPiaoData> guPiaoDataListTemp = new
			 * ArrayList<GuPiaoData>(); guPiaoDataListTemp.add(guPiaoData1);
			 * guPiaoDataMap.put(shoupanzhangfuFloor, guPiaoDataListTemp); }
			 * if(shoupanzhangfu>2 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * 
			 * if(shoupanzhangfu>1 && (zuidazhangfu-shoupanzhangfu>4)){
			 * System.out
			 * .println("1"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * if(shoupanzhangfu>0 && (zuidazhangfu-shoupanzhangfu>5)){
			 * System.out
			 * .println("2"+guPiaoData1.getName()+"("+guPiaoData1.getCode
			 * ()+")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * if(guPiaoData1.getZuidi()>= guPiaoData1.getKaipan() &&
			 * (shoupanzhangfu+1.5>=zuidazhangfu)){
			 * System.out.println(guPiaoData1
			 * .getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu+",最高涨幅"
			 * +zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * if(zuidizhangfu<-3 && shoupanzhangfu > 0 && shoupanzhangfu < 1 ){
			 * System
			 * .out.println(guPiaoData1.getName()+"("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"
			 * +shoupanzhangfu+",最高涨幅"+zuidazhangfu+",最低涨幅:"+zuidizhangfu); }
			 * 
			 * //System.out.println("Main.readExcel()"); if(zuidizhangfu<-3 &&
			 * (kaipanzhangfu<1 && kaipanzhangfu>-1) shoupanzhangfu <0.5 &&
			 * shoupanzhangfu > -0.5 ){
			 * System.out.println(guPiaoData1.getName()+
			 * "("+guPiaoData1.getCode()
			 * +")"+"收盘涨幅:"+shoupanzhangfu+",最高涨幅"+zuidazhangfu
			 * +",最低涨幅:"+zuidizhangfu); }
			 * 
			 * }
			 */
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			try {
				// 提交失败，执行回滚操作
				conn.rollback();

			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("updateExistsInfo回滚执行失败!!!");
			}

			ex.printStackTrace();
			System.err.println("updateExistsInfo执行失败");

		} 
		// System.out.println(guPiaoDataMap);

		/*
		 * Set<Entry<String,Integer>> hangyeSet = hangyeMap.entrySet();
		 * 
		 * 
		 * for (Iterator iterator = hangyeSet.iterator(); iterator.hasNext();) {
		 * Entry<String,Integer> entry = (Entry<String,Integer>) iterator
		 * .next(); System.out.println(entry.getKey()+"   "+entry.getValue());
		 * 
		 * }
		 * 
		 * Set<Entry<Integer, List<GuPiaoData>>> set = guPiaoDataMap.entrySet();
		 * 
		 * for (Iterator iterator = set.iterator(); iterator.hasNext();) {
		 * Entry<Integer, List<GuPiaoData>> entry = (Entry<Integer,
		 * List<GuPiaoData>>) iterator .next();
		 * System.out.println(entry.getKey()+"   "+entry.getValue().size());
		 * 
		 * }
		 */
	}

	public static void readZiJinExcel(String date) throws BiffException,
			IOException, SQLException {
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
			// {3日换手%=36, 总股本=30, 昨收=18, 3日涨幅%=34, 代码=1, 涨速%=10, 流通市值=33, 量比=3,
			// 6日换手%=37, 涨跌=5, 流通股本=32, 名称=2, 金额=12, 总市值=31, 卖出价=9, 最低=16,
			// 买入价=8, 委比%=21, 卖一量=28, 序=0, 最高=15, 市净率=29, 最新=20, 内盘=24, 市盈率=13,
			// 涨幅%=4, 6日涨幅%=35, 换手%=11, 现手=7, 买一量=27, 外盘=25, 内外比=26, 振幅%=19,
			// 委差=22, 总手=6, 所属行业=14, 均价=23, 开盘=17}

			// 行数(表头的目录不需要，从1开始)
			// for (int i = 1; i < sheet.getRows(); i++) {
			// String date = new SimpleDateFormat("yyyy-MM-dd").format(new
			// Date());
			String sql = "delete from `stock_zijin` where date_format(create_date,'%Y-%m-%d') =  ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, date);
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
				pstmt.setObject(sheet.getColumns() + 1, date);
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
				System.err.println("updateExistsInfo回滚执行失败!!!");
			}

			ex.printStackTrace();
			System.err.println("updateExistsInfo执行失败");

		}
		// System.out.println(guPiaoDataMap);

		/*
		 * Set<Entry<String,Integer>> hangyeSet = hangyeMap.entrySet();
		 * 
		 * 
		 * for (Iterator iterator = hangyeSet.iterator(); iterator.hasNext();) {
		 * Entry<String,Integer> entry = (Entry<String,Integer>) iterator
		 * .next(); System.out.println(entry.getKey()+"   "+entry.getValue());
		 * 
		 * }
		 * 
		 * Set<Entry<Integer, List<GuPiaoData>>> set = guPiaoDataMap.entrySet();
		 * 
		 * for (Iterator iterator = set.iterator(); iterator.hasNext();) {
		 * Entry<Integer, List<GuPiaoData>> entry = (Entry<Integer,
		 * List<GuPiaoData>>) iterator .next();
		 * System.out.println(entry.getKey()+"   "+entry.getValue().size());
		 * 
		 * }
		 */
	}
	/*
	 * 加权平均指数（ＤＩ）=（当日最高指数+当日收盘指数+2倍的当日最低指数） 　　十二日平滑系数（Ｌ１２）=2/（12+1）=0.1538
	 * 　　二十六日平滑系数（Ｌ２６）=2/（26+1）=0.0741
	 * 　　十二日指数平均值（１２日ＥＭＡ）=L12×当日收盘指数+11/（12+1）×昨日的12日EMA
	 * 　　二十六日指数平均值（２６日ＥＭＡ）=L26×当日收盘指数+25/（26+1）×昨日的26日EMA
	 * 　　差离率（ＤＩＦ）=12日EMA-26日EMA 　　九日DIF平均值（DEA） =最近9日的DIF之和/9 　　柱状值（ＢＡＲ）=DIF-DEA
	 * 　　ＭＡＣＤ=（当日的DIF-昨日的DIF）×0.2+昨日的MACD
	 */

	
}
