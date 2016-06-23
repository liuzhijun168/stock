package com.lzj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RongQuanServlet
 */
public class RongQuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RongQuanServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		readExcel();
		response.sendRedirect("/stock/bootstrap/rongzirongquanyue.jsp");

	}

	public static void main(String[] args) {
		readExcel();
	}

	public static void readExcel() {
		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(
									"C:/Users/Administrator/Documents/"+currentDate+" 股份查询.txt"),
							"GBK"));

			String sql = "update  `rongquan` set amount=?,used=?,price=?,create_date=? where code=?";
			pstmt = conn.prepareStatement(sql);
			String line = null;
			;
			br.readLine();
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] dataArr = line.split("        ");
				int code = Integer.parseInt(dataArr[4].trim());
				int amount = Integer.parseInt(dataArr[1].trim());
				int used = Integer.parseInt(dataArr[2].trim());
				float price = Float.parseFloat(dataArr[3].trim());
				pstmt.setObject(1, amount);
				pstmt.setObject(2, used);
				pstmt.setObject(3, price);
				pstmt.setObject(4, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
				pstmt.setObject(5, code);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
