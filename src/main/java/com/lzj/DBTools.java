package com.lzj;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lzj.util.PropsUtil;

public class DBTools {

	private static final String DB_RUL = "db_url";
	private static Connection connection;
	
	public static Statement getStatement() {
		try {
			Connection con = getConn();
			Statement stmt = con.createStatement();
			return stmt;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Connection getConn() {
		if(connection != null){
			try {
				if(connection.isClosed()){
					connection = getCreateConn();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		connection = getCreateConn();

		return connection;
	}
	
	public static Connection getCreateConn(){
		Connection connection = null;
		try {
			PropsUtil propsUtil = new PropsUtil();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = propsUtil.get(DB_RUL);
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static int updateData(String sql){
		try {
			return DBTools.getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getString(String sql) {
		try {
			ResultSet resultSet = DBTools.getStatement().executeQuery(sql);
			while (resultSet.next()) {
				return resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-99.99";
	}
	
	public static java.util.Date getDate(String sql) {
		try {
			ResultSet resultSet = DBTools.getStatement().executeQuery(sql);
			while (resultSet.next()) {
				java.sql.Date resultDate = resultSet.getDate(1);
				return new Date(resultDate.getTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ResultSet getResult(String sql) {
		try {
			ResultSet resultSet = DBTools.getStatement().executeQuery(sql);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
