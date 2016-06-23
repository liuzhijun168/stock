package com.lzj;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> stockCodeList = null;

	// private String currentDate = new
	// SimpleDateFormat("yyyy-MM-dd").format(new Date());

	@Override
	public void init() throws ServletException {
		super.init();
		stockCodeList = DataTools.getStockCodeList();
		Thread thread =  new Thread(new Runnable() {
			public void run() {
				DataTools.loadLastestData();
			}
		});
		thread.start();

	}

	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public static void main(String[] args) {
		System.out.println("九 芝 堂,18.08,18.20,17.36,18.17,17.20,17.36,17.38,6928131,122246489.05,46400,17.36,23200,17.35,4182,17.34,13000,17.32,3500,17.31,13390,17.38,4600,17.39,10000,17.40,19200,17.41,21200,17.42,2014-12-29,15:05:55,00".split(",").length);
	}
}
