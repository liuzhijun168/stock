package com.lzj.eastmoney.http;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lzj.eastmoney.data.StockNotice;
import com.lzj.util.DateUtil;
import com.lzj.util.JsonUtil;

public class JsopMain {

	public static void main(String[] args) {

		Document doc;
		try {
			String url = "http://data.eastmoney.com/Notice/Noticelist.aspx?type=7&market=sh_a&date=&page=2";
			doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(".tableCont").select("tbody").select("tr");
			for (Element element : items) {
				Elements tds = element.select("td");
				StockNotice stockNotice = new StockNotice();
				stockNotice.setStockCode(tds.get(0).text());
				stockNotice.setStockName(tds.get(1).text());
				stockNotice.setTitle(tds.get(3).select("a").attr("title"));
				stockNotice.setType(tds.get(4).text());
				stockNotice.setNoticeDate(DateUtil.str2Date(tds.get(5).text(), "yyyy-MM-dd"));
				stockNotice.setCreateDate(new Date());
				System.out.println(JsonUtil.toJosn(stockNotice));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
