package com.lzj.util;

public class StockUtil {

	public static String getFullStockCode(String stockCode){
		// 6开头的是上海,0/3开头深圳

		if(stockCode.length() > 6){
			return stockCode;
		}
		
		if (stockCode.startsWith("6")) {
			stockCode = "sh" + stockCode;
		}else if (stockCode.startsWith("0") || stockCode.startsWith("3")) {
			stockCode = "sz" + stockCode;
		} else {
			return null;
		}    
		return stockCode;
	}
	
	public static String getEastMoneyStockCode(String stockCode){
		// 6开头的是上海,0/3开头深圳
		if (stockCode.startsWith("6")) {
			stockCode = stockCode+"1";
		}if (stockCode.startsWith("0") || stockCode.startsWith("3")) {
			stockCode = stockCode+"2";
		} else {
			return null;
		}    
		return stockCode;
	}
	
}
