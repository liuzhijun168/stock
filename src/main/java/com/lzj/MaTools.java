package com.lzj;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Queue;

public class MaTools {
	public static float getMa(Queue<Float> floats, int maType) {

		float sumFloat = 0;
		for (Float floatData : floats) {
			sumFloat = sumFloat + floatData;
		}
		sumFloat = sumFloat / maType;
		String e = String.format("%.2f", sumFloat);
		if ("Infinity".equals(e)) {
			e = "-99.99";
		}

		return Float.parseFloat(e);

	}

	public static float setMa(Queue<Float> queue, int maType, float price) {
		if (queue.size() >= maType) {
			queue.poll();
			queue.offer(price);
		} else {
			queue.offer(price);
		}
		return MaTools.getMa(queue, maType);
	}

	public static float getMa(int maDay, String stockCode) {
		float maValue = 0;
		ResultSet resultSet;
		try {
			resultSet = DBTools
					.getStatement()
					.executeQuery(
							"select ROUND(sum(d)/"
									+ maDay
									+ ",2) ma from ( select d from stock_data_day where  b='"
									+ stockCode
									+ "' order by create_date desc limit 0,"
									+ maDay + "  ) t ");
			while (resultSet.next()) {
				maValue = resultSet.getFloat("ma");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maValue;
	}

	public static void updateMa1(String date) {
		List<String> stockCodes = DataTools.getStockCodeList();
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		String sql = "update stock_data_day set m5=?,m10=?,m20=?,m30=?,m60=?,m120=?,m250=? where b = ? and date_format(create_date,'%Y-%m-%d') = ?";

		try {
			for (int i = 0; i < stockCodes.size(); i++) {
				String stockCode = stockCodes.get(i);

				float ma5 = MaTools.getMa(5, stockCode);
				float ma10 = MaTools.getMa(10, stockCode);
				float ma20 = MaTools.getMa(20, stockCode);
				float ma30 = MaTools.getMa(30, stockCode);
				float ma60 = MaTools.getMa(60, stockCode);
				float ma120 = MaTools.getMa(120, stockCode);
				float ma250 = MaTools.getMa(250, stockCode);
				pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, ma5);
				pstmt.setObject(2, ma10);
				pstmt.setObject(3, ma20);
				pstmt.setObject(4, ma30);
				pstmt.setObject(5, ma60);
				pstmt.setObject(6, ma120);
				pstmt.setObject(7, ma250);
				pstmt.setObject(8, stockCode);
				pstmt.setObject(9, date);
				System.out.println(stockCode + "  " + i);
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void updateMa() {
		List<String> stockCodes = DataTools.getStockCodeList();
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		String sql = "update stock_data_query set m5=?,m10=?,m20=?,m30=?,m60=?,m120=?,m250=? where b = ? ";

		try {
			for (int i = 0; i < stockCodes.size(); i++) {
				String stockCode = stockCodes.get(i);

				float ma5 = MaTools.getMa(5, stockCode);
				float ma10 = MaTools.getMa(10, stockCode);
				float ma20 = MaTools.getMa(20, stockCode);
				float ma30 = MaTools.getMa(30, stockCode);
				float ma60 = MaTools.getMa(60, stockCode);
				float ma120 = MaTools.getMa(120, stockCode);
				float ma250 = MaTools.getMa(250, stockCode);
				pstmt = conn.prepareStatement(sql);
				pstmt.setObject(1, ma5);
				pstmt.setObject(2, ma10);
				pstmt.setObject(3, ma20);
				pstmt.setObject(4, ma30);
				pstmt.setObject(5, ma60);
				pstmt.setObject(6, ma120);
				pstmt.setObject(7, ma250);
				pstmt.setObject(8, stockCode);
				System.out.println(stockCode + "  " + i);
				pstmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void copyMa(String date) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		String sql = "update stock_data_day set m5=?,m10=?,m20=?,m30=?,m60=?,m120=?,m250=? where b = ? and date_format(create_date,'%Y-%m-%d') = ?  ";

		try {
			// for (int i = 0; i < stockCodes.size(); i++) {
			String stockCode = "";// stockCodes.get(i);

			ResultSet resultSet = conn
					.createStatement()
					.executeQuery(
							"select b,m5,m10,m20,m30,m60,m120,m250 from stock_data_query where date_format(create_date,'%Y-%m-%d') = '"
									+ date + "' ");
			int i = 0;
			while (resultSet.next()) {
				pstmt = conn.prepareStatement(sql);
				stockCode = resultSet.getString("b");
				pstmt.setObject(1, resultSet.getString("m5"));
				pstmt.setObject(2, resultSet.getString("m10"));
				pstmt.setObject(3, resultSet.getString("m20"));
				pstmt.setObject(4, resultSet.getString("m30"));
				pstmt.setObject(5, resultSet.getString("m60"));
				pstmt.setObject(6, resultSet.getString("m120"));
				pstmt.setObject(7, resultSet.getString("m250"));
				pstmt.setObject(8, stockCode);
				pstmt.setObject(9, date);
				System.out.println(stockCode + "  " + i++);
				pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void copyField() {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		String sql = "update stock_data_query set g=? where b = ? and date_format(create_date,'%Y-%m-%d') = '2015-01-06'  ";

		try {
			// for (int i = 0; i < stockCodes.size(); i++) {
			String stockCode = "";// stockCodes.get(i);

			ResultSet resultSet = conn
					.createStatement()
					.executeQuery(
							"select b,create_date,g from stock_data where date_format(create_date,'%Y-%m-%d') = '2015-01-06'");
			int i = 0;
			while (resultSet.next()) {
				pstmt = conn.prepareStatement(sql);
				stockCode = resultSet.getString("b");
				pstmt.setObject(1, resultSet.getString("g"));
				pstmt.setObject(2, stockCode);
				System.out.println(stockCode + "  " + i++);
				pstmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
	public static void updateBBI() {
		
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		String sql = "update stock_data_query set bbi=? where b = ?";

		try {
			DecimalFormat df = new DecimalFormat("#.00");
			List<String> stockCodes = DataTools.getStockCodeList();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < stockCodes.size(); i++) {
				String stockCode = stockCodes.get(i);
				float ma3 = MaTools.getMa(3, stockCode);
				float ma6 = MaTools.getMa(6, stockCode);
				float ma12 = MaTools.getMa(12, stockCode);
				float ma24 = MaTools.getMa(24, stockCode);
				float bbi = Float.parseFloat(df.format((ma3+ma6+ma12+ma24)/4));
				pstmt.setObject(1, bbi);
				pstmt.setObject(2, stockCode);
				pstmt.execute();
				System.out.println(i+"  "+stockCode+"   "+bbi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
	}
}
