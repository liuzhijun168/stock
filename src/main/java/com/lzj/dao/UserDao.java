package com.lzj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lzj.DBTools;
import com.lzj.bean.User;

public class UserDao {

	public User getUser(String loginName,String password) {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		User user = null;
		try {
			conn = DBTools.getConn();
			String sql = "select * from user where loginName = ? and password = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setObject(1, loginName);
			pstmt.setObject(2, password);
			ResultSet resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLoginName(resultSet.getString("loginName"));
				user.setPassword(resultSet.getString("password"));
				user.setNickname(resultSet.getString("nickname"));
			}
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
		return user;
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
