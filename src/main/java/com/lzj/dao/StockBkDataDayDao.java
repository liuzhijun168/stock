package com.lzj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.lzj.DBTools;
import com.lzj.bean.Blotter;
import com.lzj.bean.StockBkDataDay;
import com.lzj.bean.StockDataDay;
import com.lzj.util.DateUtil;

public class StockBkDataDayDao {

	public List<StockBkDataDay> getByCreateDate(Date createDate) {
		List<StockBkDataDay> stockBkDataDays = new ArrayList<StockBkDataDay>();

		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "select * from stock_bk_data_day sbdd where sbdd.create_date = ? order by e desc";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, DateUtil.getDateBegin(createDate));
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				StockBkDataDay stockBkDataDay = new StockBkDataDay();
				stockBkDataDay.setId(resultSet.getInt("id"));
				stockBkDataDay.setB(resultSet.getString("b"));
				stockBkDataDay.setC(resultSet.getString("c"));
				stockBkDataDay.setD(resultSet.getDouble("d"));
				stockBkDataDay.setE(resultSet.getDouble("e"));
				stockBkDataDay.setR(resultSet.getDouble("r"));
				stockBkDataDay.setP(resultSet.getDouble("p"));
				stockBkDataDay.setQ(resultSet.getDouble("q"));
				stockBkDataDay.setS(resultSet.getDouble("s"));
				stockBkDataDay.setO(resultSet.getDouble("o"));
				stockBkDataDay.setM(resultSet.getDouble("m"));
				stockBkDataDay.setM5(resultSet.getDouble("m5"));
				stockBkDataDay.setM10(resultSet.getDouble("m10"));
				stockBkDataDay.setM20(resultSet.getDouble("m20"));
				stockBkDataDay.setM30(resultSet.getDouble("m30"));
				stockBkDataDay.setM60(resultSet.getDouble("m60"));
				stockBkDataDay.setM120(resultSet.getDouble("m120"));
				stockBkDataDay.setM250(resultSet.getDouble("m250"));
				stockBkDataDay.setCreateDate(resultSet.getDate("sbdd.create_date"));
				stockBkDataDays.add(stockBkDataDay);

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
		return stockBkDataDays;

	}

	public List<StockBkDataDay> getLineByBkCode(Date createDate,String bkCode) {
		List<StockBkDataDay> stockBkDataDays = new ArrayList<StockBkDataDay>();

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
			String sql = "select * from stock_bk_data_day sbdd where sbdd.create_date >= ? and b = ? order by create_date asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setObject(1, DateUtil.getDateBegin(createDate));
			pstmt.setObject(2, bkCode);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				StockBkDataDay stockBkDataDay = new StockBkDataDay();
				stockBkDataDay.setId(resultSet.getInt("id"));
				stockBkDataDay.setB(resultSet.getString("b"));
				stockBkDataDay.setC(resultSet.getString("c"));
				stockBkDataDay.setD(resultSet.getDouble("d"));
				stockBkDataDay.setE(resultSet.getDouble("e"));
				stockBkDataDay.setR(resultSet.getDouble("r"));
				stockBkDataDay.setP(resultSet.getDouble("p"));
				stockBkDataDay.setQ(resultSet.getDouble("q"));
				stockBkDataDay.setS(resultSet.getDouble("s"));
				stockBkDataDay.setO(resultSet.getDouble("o"));
				stockBkDataDay.setM(resultSet.getDouble("m"));
				stockBkDataDay.setM5(resultSet.getDouble("m5"));
				stockBkDataDay.setM10(resultSet.getDouble("m10"));
				stockBkDataDay.setM20(resultSet.getDouble("m20"));
				stockBkDataDay.setM30(resultSet.getDouble("m30"));
				stockBkDataDay.setM60(resultSet.getDouble("m60"));
				stockBkDataDay.setM120(resultSet.getDouble("m120"));
				stockBkDataDay.setM250(resultSet.getDouble("m250"));
				stockBkDataDay.setCreateDate(resultSet.getDate("sbdd.create_date"));
				stockBkDataDays.add(stockBkDataDay);

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
		return stockBkDataDays;

	}
	public void addBlotter(Blotter blotter) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "insert blotter(szzs,balance,balance_yy,create_date) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, blotter.getSzzs());
			pstmt.setObject(2, blotter.getBalance());
			pstmt.setObject(3, blotter.getBalanceYy());
			pstmt.setObject(4, blotter.getCreateDate());
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

	public void delBlotter(int blotterId) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "delete from  blotter where id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, blotterId);
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

	public void modifyBlotterBalance(float balance) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "update blotter set balance = balance + ?,balance_yy = balance_yy + ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, balance);
			pstmt.setObject(2, balance);
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

	public Date getLastModifyDate() {
		return DBTools.getDate("select create_date from stock_bk_data_day order by create_date desc limit 1");
	}


	public String getNextDateByCreateDate(String currentDate) {
		return DBTools.getString("select create_date from stock_bk_data_day where create_date > '"+currentDate+"' group by create_date order by create_date asc limit 1");
	}

	public String getPreDateByCreateDate(String currentDate) {
		return DBTools.getString("	select create_date from stock_bk_data_day where create_date < '"+currentDate+"' group by create_date order by create_date desc limit 1");
	}

	public List<String> getBkCodeList(String bkNamePrefix){
		List<String> stockBkCodeList = new ArrayList<String>();

		String currentDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		String preDate = getPreDateByCreateDate(currentDate );
		
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "select b from stock_bk_data_day sbdd where sbdd.create_date = ? and b like '"+bkNamePrefix+"%' order by create_date asc";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setObject(1, preDate);
			
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
	
	public void addStockBkDataDay(StockDataDay stockDataDay) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_bk_data_day`(b,c,r,p,q,d,o,m,s,e,m5,m10,m20,m30,m60,m120,m250,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			stockDataDay.getD();
			pstmt.setObject(1, stockDataDay.getB());
			pstmt.setObject(2, stockDataDay.getC());
			pstmt.setObject(3, stockDataDay.getR());
			pstmt.setObject(4, stockDataDay.getP());
			pstmt.setObject(5, stockDataDay.getQ());
			pstmt.setObject(6, stockDataDay.getD());
			pstmt.setObject(7, stockDataDay.getO());
			pstmt.setObject(8, stockDataDay.getM());
			pstmt.setObject(9, stockDataDay.getS());
			pstmt.setObject(10, stockDataDay.getE());
			pstmt.setObject(11, stockDataDay.getM5());
			pstmt.setObject(12, stockDataDay.getM10());
			pstmt.setObject(13, stockDataDay.getM20());
			pstmt.setObject(14, stockDataDay.getM30());
			pstmt.setObject(15, stockDataDay.getM60());
			pstmt.setObject(16, stockDataDay.getM120());
			pstmt.setObject(17, stockDataDay.getM250());
			pstmt.setObject(18, stockDataDay.getCreateDate());
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
