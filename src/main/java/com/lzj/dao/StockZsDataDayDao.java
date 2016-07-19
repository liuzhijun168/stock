package com.lzj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lzj.DBTools;
import com.lzj.bean.StockZsDataDay;
import com.lzj.util.DateUtil;

public class StockZsDataDayDao {

	public List<StockZsDataDay> getByCreateDate(Date createDate) {
		List<StockZsDataDay> stockZsDataDays = new ArrayList<StockZsDataDay>();

		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "select * from stock_zs_data_day szdd where sbdd.create_date = ? order by e desc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, DateUtil.getDateBegin(createDate));
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				StockZsDataDay stockZsDataDay = new StockZsDataDay();
				stockZsDataDay.setId(resultSet.getInt("id"));
				stockZsDataDay.setB(resultSet.getString("b"));
				stockZsDataDay.setC(resultSet.getString("c"));
				stockZsDataDay.setD(resultSet.getDouble("d"));
				stockZsDataDay.setE(resultSet.getDouble("e"));
				stockZsDataDay.setR(resultSet.getDouble("r"));
				stockZsDataDay.setP(resultSet.getDouble("p"));
				stockZsDataDay.setQ(resultSet.getDouble("q"));
				stockZsDataDay.setS(resultSet.getDouble("s"));
				stockZsDataDay.setO(resultSet.getDouble("o"));
				stockZsDataDay.setM(resultSet.getDouble("m"));
				stockZsDataDay.setM5(resultSet.getDouble("m5"));
				stockZsDataDay.setM10(resultSet.getDouble("m10"));
				stockZsDataDay.setM20(resultSet.getDouble("m20"));
				stockZsDataDay.setM30(resultSet.getDouble("m30"));
				stockZsDataDay.setM60(resultSet.getDouble("m60"));
				stockZsDataDay.setM120(resultSet.getDouble("m120"));
				stockZsDataDay.setM250(resultSet.getDouble("m250"));
				stockZsDataDay.setCreateDate(resultSet.getDate("szdd.create_date"));
				stockZsDataDays.add(stockZsDataDay);

			}
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*
				 * if (conn != null) conn.close();
				 */
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}
		return stockZsDataDays;

	}

	public List<StockZsDataDay> getLineByZsCode(Date createDate,String bkCode) {
		List<StockZsDataDay> stockZsDataDays = new ArrayList<StockZsDataDay>();

		/*if(CollectionUtils.isEmpty(bkCodeList)){
			return null;
		}*/
		
	/*	StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bkCodeList.size(); i++) {
			String bkCode = bkCodeList.get(i);
			if(i == (bkCodeList.size()-1)){
				buffer.append("'"+bkCode+"'");
			}else{
				buffer.append("'"+bkCode+"'").append(",");
			}
		}*/
		
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "select * from stock_zs_data_day szdd where szdd.create_date >= ? and b = ? order by create_date asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setObject(1, DateUtil.getDateBegin(createDate));
			pstmt.setObject(2, bkCode);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				StockZsDataDay stockZsDataDay = new StockZsDataDay();
				stockZsDataDay.setId(resultSet.getInt("id"));
				stockZsDataDay.setB(resultSet.getString("b"));
				stockZsDataDay.setC(resultSet.getString("c"));
				stockZsDataDay.setD(resultSet.getDouble("d"));
				stockZsDataDay.setE(resultSet.getDouble("e"));
				stockZsDataDay.setR(resultSet.getDouble("r"));
				stockZsDataDay.setP(resultSet.getDouble("p"));
				stockZsDataDay.setQ(resultSet.getDouble("q"));
				stockZsDataDay.setS(resultSet.getDouble("s"));
				stockZsDataDay.setO(resultSet.getDouble("o"));
				stockZsDataDay.setM(resultSet.getDouble("m"));
				stockZsDataDay.setM5(resultSet.getDouble("m5"));
				stockZsDataDay.setM10(resultSet.getDouble("m10"));
				stockZsDataDay.setM20(resultSet.getDouble("m20"));
				stockZsDataDay.setM30(resultSet.getDouble("m30"));
				stockZsDataDay.setM60(resultSet.getDouble("m60"));
				stockZsDataDay.setM120(resultSet.getDouble("m120"));
				stockZsDataDay.setM250(resultSet.getDouble("m250"));
				stockZsDataDay.setCreateDate(resultSet.getDate("szdd.create_date"));
				stockZsDataDays.add(stockZsDataDay);

			}
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*
				 * if (conn != null) conn.close();
				 */
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}
		return stockZsDataDays;

	}

	public Date getLastModifyDate() {
		return DBTools.getDate("select create_date from stock_zs_data_day order by create_date desc limit 1");
	}


	public String getNextDateByCreateDate(String currentDate) {
		return DBTools.getString("select create_date from stock_zs_data_day where create_date > '"+currentDate+"' group by create_date order by create_date asc limit 1");
	}

	public String getPreDateByCreateDate(String currentDate) {
		return DBTools.getString("	select create_date from stock_zs_data_day where create_date < '"+currentDate+"' group by create_date order by create_date desc limit 1");
	}

	public List<String> getZsCodeList(){
		List<String> stockBkCodeList = new ArrayList<String>();

		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "select b from stock_zs_data_day group by b";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				stockBkCodeList.add(resultSet.getString("b"));
			}
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*
				 * if (conn != null) conn.close();
				 */
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}
		return stockBkCodeList;
	}
	
	public void addStockZsDataDay(StockZsDataDay stockZsDataDay) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_zs_data_day`(b,c,r,p,q,d,o,m,s,e,m5,m10,m20,m30,m60,m120,m250,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			stockZsDataDay.getD();
			pstmt.setObject(1, stockZsDataDay.getB());
			pstmt.setObject(2, stockZsDataDay.getC());
			pstmt.setObject(3, stockZsDataDay.getR());
			pstmt.setObject(4, stockZsDataDay.getP());
			pstmt.setObject(5, stockZsDataDay.getQ());
			pstmt.setObject(6, stockZsDataDay.getD());
			pstmt.setObject(7, stockZsDataDay.getO());
			pstmt.setObject(8, stockZsDataDay.getM());
			pstmt.setObject(9, stockZsDataDay.getS());
			pstmt.setObject(10, stockZsDataDay.getE());
			pstmt.setObject(11, stockZsDataDay.getM5());
			pstmt.setObject(12, stockZsDataDay.getM10());
			pstmt.setObject(13, stockZsDataDay.getM20());
			pstmt.setObject(14, stockZsDataDay.getM30());
			pstmt.setObject(15, stockZsDataDay.getM60());
			pstmt.setObject(16, stockZsDataDay.getM120());
			pstmt.setObject(17, stockZsDataDay.getM250());
			pstmt.setObject(18, stockZsDataDay.getCreateDate());
			pstmt.executeUpdate();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*
				 * if (conn != null) conn.close();
				 */
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
}
