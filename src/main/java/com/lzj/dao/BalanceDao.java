package com.lzj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lzj.DBTools;
import com.lzj.bean.Balance;

public class BalanceDao {

	public void addBalance(Balance balance) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);
			String sql = "insert balance(balance,remark,create_date) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, balance.getBalance());
			pstmt.setObject(2, balance.getRemark());
			pstmt.setObject(3, balance.getCreateDate());
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
}
