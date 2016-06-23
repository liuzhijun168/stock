package com.lzj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class TdxMain {
	public static void main(String[] args) throws IOException {

		Connection conn = DBTools.getConn();
		PreparedStatement pstmt = null;
		try {
			conn = DBTools.getConn();
			conn.setAutoCommit(false);

			File dirFile = new File("D:/tools/new_tdx/T0002/export");
			File[] files = dirFile.listFiles();

			File tempFile = null;
			for (int i = 0; i < files.length; i++) {
				tempFile = files[i];
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(tempFile), "GBK"));
				String line = "";

				String code = tempFile.getName().substring(2, 8);
				String name = br.readLine().substring(7, 11);
				System.out.println(name);
				String sql = "INSERT INTO `stock_data_day`(b,c,r,p,q,d,o,m,s,e,m5,m10,m20,m30,m60,m120,m250,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				float zuoshou = 0;
				Queue<Float> queue5 = new LinkedList<Float>();
				Queue<Float> queue10 = new LinkedList<Float>();
				Queue<Float> queue20 = new LinkedList<Float>();
				Queue<Float> queue30 = new LinkedList<Float>();
				Queue<Float> queue60 = new LinkedList<Float>();
				Queue<Float> queue120 = new LinkedList<Float>();
				Queue<Float> queue250 = new LinkedList<Float>();
				while ((line = br.readLine()) != null) {
					if (line.contains(",")) {
						String[] dataArr = line.split(",");
						String createDate = dataArr[0];
						// b代码
						String b = code;
						// c名称
						String c = name;
						// r开盘
						String r = dataArr[1];
						// p最高
						String p = dataArr[2];
						// q最低
						String q = dataArr[3];
						// d最新
						String d = dataArr[4];
						// e涨幅
						String e = String
								.format("%.2f", (Float.parseFloat(d) - zuoshou)
										* 100 / zuoshou);
						if ("Infinity".equals(e)) {
							e = "-99.99";
						}

						// s昨收
						zuoshou = Float.parseFloat(d);
						// o总手
						String o = dataArr[5];
						// m金额
						String m = dataArr[6];

						// System.out.println(line);
						// System.out.println(createDate+"-"+b+"-"+c+"-"+r+"-"+p+"-"+q+"-"+d+"-"+o+"-"+m);
						// String sql =
						// "delete from `stock_data_day` where date_format(create_date,'%Y-%m-%d') =  ? ";
						/*
						 * pstmt = conn.prepareStatement(sql);
						 * pstmt.setObject(1, createDate); pstmt.execute();
						 */
						pstmt.setObject(1, b);
						pstmt.setObject(2, c);
						pstmt.setObject(3, r);
						pstmt.setObject(4, p);
						pstmt.setObject(5, q);
						pstmt.setObject(6, d);
						pstmt.setObject(7, o);
						pstmt.setObject(8, m);
						pstmt.setObject(9, zuoshou);
						pstmt.setObject(10, e);
						pstmt.setObject(11, MaTools.setMa(queue5, 5, Float.parseFloat(d)));
						pstmt.setObject(12, MaTools.setMa(queue10, 10, Float.parseFloat(d)));
						pstmt.setObject(13, MaTools.setMa(queue20, 20, Float.parseFloat(d)));
						pstmt.setObject(14, MaTools.setMa(queue30, 30, Float.parseFloat(d)));
						pstmt.setObject(15, MaTools.setMa(queue60, 60, Float.parseFloat(d)));
						pstmt.setObject(16, MaTools.setMa(queue120, 120, Float.parseFloat(d)));
						pstmt.setObject(17, MaTools.setMa(queue250, 250, Float.parseFloat(d)));
						pstmt.setObject(18, createDate);
						pstmt.addBatch();
					}
				}

				br.close();
				br.close();
				pstmt.executeBatch();
				conn.commit();
			}

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
				if (conn != null)
					conn.close();
				System.out.println("成功");
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("资源关闭失败!!!");
			}
		}

	}
	
	
}
