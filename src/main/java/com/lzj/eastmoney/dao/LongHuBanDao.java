package com.lzj.eastmoney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.lzj.DBTools;
import com.lzj.eastmoney.data.StockHolder;
import com.lzj.eastmoney.data.StockNotice;
import com.lzj.eastmoney.data.josn.LongHuBanGeGuJosn;
import com.lzj.eastmoney.data.josn.LongHuBanJosn;
import com.lzj.eastmoney.data.josn.TiCaiDetail;
import com.lzj.eastmoney.data.josn.YingYeBuJson;

public class LongHuBanDao {
	
	public void addLongHuBanList(List<LongHuBanJosn> longHuBanJosns) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_lhb`(SalesCode,SalesName,SumActBMoney,SumActSMoney,SumActMoney,BCount,SCount,UpCount) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (LongHuBanJosn longHuBan : longHuBanJosns) {
			
				pstmt.setObject(1, longHuBan.getSalesCode());
				pstmt.setObject(2, longHuBan.getSalesName());
				pstmt.setObject(3, longHuBan.getSumActBMoney());
				pstmt.setObject(4, longHuBan.getSumActSMoney());
				pstmt.setObject(5, longHuBan.getSumActMoney());
				pstmt.setObject(6, longHuBan.getBCount());
				pstmt.setObject(7, longHuBan.getSCount());
				pstmt.setObject(8, longHuBan.getUpCount());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
	
	public void addYingYeBuList(List<YingYeBuJson> yingYeBuJsons) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_yyb`(Province,SalesCode,SalesName,SumActBMoney,SumActSMoney,SumActMoney,BCount,SCount,UpCount) VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (YingYeBuJson yingYeBu : yingYeBuJsons) {
			
				pstmt.setObject(1, yingYeBu.getProvince());
				pstmt.setObject(2, yingYeBu.getSalesCode());
				pstmt.setObject(3, yingYeBu.getSalesName());
				pstmt.setObject(4, yingYeBu.getSumActBMoney());
				pstmt.setObject(5, yingYeBu.getSumActSMoney());
				pstmt.setObject(6, yingYeBu.getSumActMoney());
				pstmt.setObject(7, yingYeBu.getBCount());
				pstmt.setObject(8, yingYeBu.getSCount());
				pstmt.setObject(9, yingYeBu.getUpCount());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
	
	public void addLongHuBanGeGuList(List<LongHuBanGeGuJosn> longHuBanGeGuJosns) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_lhb_gegu`(BMoney,SMoney,RChange3M,RChange3DC,RChange20DC,RChange15DC,RChange10DC,RChange5DC,RChange20DO,RChange2DC,"
					+ "RChange30DC,RChange15DO,RChange3DO,RChange10DO,RChange5DO,RChange1DO,RChange30DO,"
					+ "RChange1DC,RChange2DO,RChange1Y,RChange1M,RChange6M,SalesName,SalesCode,SCode,"
					+ "SName,ActSellNum,ActBuyNum,ChgRadio,CPrice,PBuy,TDate,CTypeDes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (LongHuBanGeGuJosn longHuBanGeGu : longHuBanGeGuJosns) {
				pstmt.setObject(1, longHuBanGeGu.getBMoney());
				pstmt.setObject(2, longHuBanGeGu.getSMoney());
				pstmt.setObject(3, longHuBanGeGu.getRChange3M());
				pstmt.setObject(4, longHuBanGeGu.getRChange3DC());
				pstmt.setObject(5, longHuBanGeGu.getRChange20DC());
				pstmt.setObject(6, longHuBanGeGu.getRChange15DC());
				pstmt.setObject(7, longHuBanGeGu.getRChange10DC());
				pstmt.setObject(8, longHuBanGeGu.getRChange5DC());
				pstmt.setObject(9, longHuBanGeGu.getRChange20DO());
				pstmt.setObject(10, longHuBanGeGu.getRChange2DC());
				pstmt.setObject(11, longHuBanGeGu.getRChange30DC());
				pstmt.setObject(12, longHuBanGeGu.getRChange15DO());
				pstmt.setObject(13, longHuBanGeGu.getRChange3DO());
				pstmt.setObject(14, longHuBanGeGu.getRChange10DO());
				pstmt.setObject(15, longHuBanGeGu.getRChange5DO());
				pstmt.setObject(16, longHuBanGeGu.getRChange1DO());
				pstmt.setObject(17, longHuBanGeGu.getRChange30DO());
				pstmt.setObject(18, longHuBanGeGu.getRChange1DC());
				pstmt.setObject(19, longHuBanGeGu.getRChange2DO());
				pstmt.setObject(20, longHuBanGeGu.getRChange1Y());
				pstmt.setObject(21, longHuBanGeGu.getRChange1M());
				pstmt.setObject(22, longHuBanGeGu.getRChange6M());
				pstmt.setObject(23, longHuBanGeGu.getSalesName());
				pstmt.setObject(24, longHuBanGeGu.getSalesCode());
				pstmt.setObject(25, longHuBanGeGu.getSCode());
				pstmt.setObject(26, longHuBanGeGu.getSName());
				pstmt.setObject(27, longHuBanGeGu.getActSellNum());
				pstmt.setObject(28, longHuBanGeGu.getActBuyNum());
				pstmt.setObject(29, longHuBanGeGu.getChgRadio());
				pstmt.setObject(30, longHuBanGeGu.getCPrice());
				pstmt.setObject(31, longHuBanGeGu.getPBuy());
				pstmt.setObject(32, longHuBanGeGu.getTDate());
				pstmt.setObject(33, longHuBanGeGu.getCTypeDes());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
	
	public void addTiCaiDetailList(List<TiCaiDetail> tiCaiDetails) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try { 
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_ticai_detail`(code,orderBy,content,create_date) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (TiCaiDetail tiCaiDetail : tiCaiDetails) {
			
				pstmt.setObject(1, tiCaiDetail.getCode());
				pstmt.setObject(2, tiCaiDetail.getOrderBy());
				pstmt.setObject(3, tiCaiDetail.getContent());
				pstmt.setObject(4, new Date());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}

	public void addStockholderList(List<StockHolder> stockHolderList) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try { 
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_guben`(stockCode,stockholderName,stockholderType,stockholderRatio,addOrSubtract,createDate) VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (StockHolder stockHolder : stockHolderList) {
			
				pstmt.setObject(1, stockHolder.getStockCode());
				pstmt.setObject(2, stockHolder.getStockholderName());
				pstmt.setObject(3, stockHolder.getStockholderType());
				pstmt.setObject(4, stockHolder.getStockholderRatio());
				pstmt.setObject(5, stockHolder.getAddOrSubtract());
				pstmt.setObject(6, new Date());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}
		
	}
	
	public void addStockNoticeList(List<StockNotice> stockNoticeList) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try { 
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO `stock_notice`(stockCode,stockName,title,type,noticeDate,createDate) VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for (StockNotice stockNotice : stockNoticeList) {
			
				pstmt.setObject(1, stockNotice.getStockCode());
				pstmt.setObject(2, stockNotice.getStockName());
				pstmt.setObject(3, stockNotice.getTitle());
				pstmt.setObject(4, stockNotice.getType());
				pstmt.setObject(5, stockNotice.getNoticeDate());
				pstmt.setObject(6, new Date());
				pstmt.addBatch();
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

		} finally {
			try {
				// 关闭资源
				if (pstmt != null)
					pstmt.close();
				/*if (conn != null)
					conn.close();*/
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}
		
	}
}
