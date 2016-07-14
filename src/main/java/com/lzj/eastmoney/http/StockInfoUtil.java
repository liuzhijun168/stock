package com.lzj.eastmoney.http;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.lzj.DataTools;
import com.lzj.eastmoney.dao.LongHuBanDao;
import com.lzj.eastmoney.data.StockHolder;
import com.lzj.eastmoney.data.StockNotice;
import com.lzj.eastmoney.data.josn.LongHuBanGeGuResultJosn;
import com.lzj.eastmoney.data.josn.LongHuBanResultJosn;
import com.lzj.eastmoney.data.josn.TiCaiDetail;
import com.lzj.eastmoney.data.josn.YingYeBuResultJson;
import com.lzj.tencent.data.josn.StockActualTimeDetail;
import com.lzj.util.DateUtil;
import com.lzj.util.HttpUtil;
import com.lzj.util.JsonUtil;
import com.lzj.util.StockUtil;

public class StockInfoUtil {

	/**
	 * 核心题材
	 */
	public static List<TiCaiDetail> getHeXinTiCai(String stockCode) {
		List<TiCaiDetail> ticaiList = new ArrayList<TiCaiDetail>();
		Document doc;
		try {
			String url = "http://f10.eastmoney.com/f10_v2/CoreConception.aspx?code="
					+ StockUtil.getFullStockCode(stockCode) + "&timetip=635994723137473541";
			doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select(".summary");
			for (Element item : items) {
				Elements links = item.select("a");
				for (Element link : links) {
					link.attr("href", link.attr("abs:href"));
				}

				Elements imgs = item.select("img");
				for (Element img : imgs) {
					img.attr("src", img.attr("abs:src"));
				}
				String html = item.html();
				String[] yaodianList = html.split("\n");

				int count = 1;
				for (String yaodian : yaodianList) {
					yaodian = yaodian.replace("<p>", "");
					yaodian = yaodian.replace("：", "#");
					yaodian = yaodian.replace("<font>", "");
					yaodian = yaodian.replace("</font>　　", "#");
					yaodian = yaodian.replace("</font>　", "#");
					yaodian = yaodian.replace("</p>", "");
					TiCaiDetail caiDetail = new TiCaiDetail();
					caiDetail.setCode(stockCode);
					caiDetail.setOrderBy(count++);
					caiDetail.setContent(yaodian);
					caiDetail.setCreateDate(new Date());
					ticaiList.add(caiDetail);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticaiList;
	}

	/**
	 * 获取龙虎榜数据
	 */
	public static LongHuBanResultJosn getLongHuBan() {

		String data = HttpUtil.getJsonContent(
				"http://data.eastmoney.com/DataCenter_V3/stock2016/TraderStatistic/pagesize=0,page=1,sortRule=-1,sortType=,startDate=2016-02-22,endDate=2016-05-22,gpfw=0,js=var%20data_tab_1.html?rt=24397550");

		data = data.replace("var data_tab_1=", "");
		LongHuBanResultJosn longHuBanResultJosn = JSON.parseObject(data, LongHuBanResultJosn.class);

		return longHuBanResultJosn;
	}

	/**
	 * 获取龙虎榜数据
	 */
	public static LongHuBanGeGuResultJosn getGeGuByLhbId(String salesCode) {

		String data = HttpUtil.getJsonContent(
				"http://data.eastmoney.com/DataCenter_V3/stock2016/jymx.ashx?pagesize=0&page=1&js=var%20ruJiktmU&param=&sortRule=-1&sortType=&gpfw=0&code="
						+ salesCode + "&rt=24398065");
		data = data.replace("var ruJiktmU=", "");
		LongHuBanGeGuResultJosn longHuBanGeGuResultJosn = JSON.parseObject(data, LongHuBanGeGuResultJosn.class);
		return longHuBanGeGuResultJosn;
	}

	/**
	 * 获取个股实时详情
	 */

	public static LongHuBanResultJosn getGeGu(String stockCode) {

		String data = HttpUtil.getJsonContent("http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id="
				+ StockUtil.getEastMoneyStockCode(stockCode));

		int index = data.indexOf("(");
		data = data.substring(index + 1, data.length() - 1);
		try {
			data = new String(data.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		LongHuBanResultJosn longHuBanResultJosn = JSON.parseObject(data, LongHuBanResultJosn.class);

		return longHuBanResultJosn;
	}

	/**
	 * 获取个股实时详情
	 */
	public static StockActualTimeDetail getStockDetail(String stockCode) {
		String data = HttpUtil.getJsonContent("http://qt.gtimg.cn/q=" + StockUtil.getFullStockCode(stockCode));
		data = data.replace("v_" + StockUtil.getFullStockCode(stockCode) + "=", "");
		String[] stockDatas = data.split("~");
		StockActualTimeDetail stockActualTimeDetail = new StockActualTimeDetail();
		stockActualTimeDetail.setName(stockDatas[1]);
		stockActualTimeDetail.setCode(stockDatas[2]);
		stockActualTimeDetail.setPrice(stockDatas[3]);
		stockActualTimeDetail.setClose(stockDatas[4]);
		stockActualTimeDetail.setOpen(stockDatas[5]);
		stockActualTimeDetail.setChg(stockDatas[32]);
		stockActualTimeDetail.setTurnoverRate(stockDatas[38]);
		stockActualTimeDetail.setPeRatio(stockDatas[39]);
		stockActualTimeDetail.setUpdatedDate(stockDatas[30]);
		stockActualTimeDetail.setMaxPrice(stockDatas[41]);
		stockActualTimeDetail.setMinPrice(stockDatas[42]);
		stockActualTimeDetail.setAmplitude(stockDatas[43]);
		stockActualTimeDetail.setMinStopPrice(stockDatas[47]);
		stockActualTimeDetail.setMaxStopPrice(stockDatas[48]);
		return stockActualTimeDetail;
	}

	/**
	 * 根据省份Code获取营业部列表
	 */
	public static YingYeBuResultJson getYingYeBuListByProvinceCode(String provinceCode) {

		String data = HttpUtil.getJsonContent(
				"http://data.eastmoney.com/DataCenter_V3/stock2016/yybSearch.ashx?pagesize=0&page=1&typeCode=2&code="
						+ provinceCode);

		YingYeBuResultJson yingYeBuResultJson = JSON.parseObject(data, YingYeBuResultJson.class);

		return yingYeBuResultJson;
	}

	public static List<StockHolder> getStockHolderList(String stockCode) {
		List<StockHolder> stockholders = new ArrayList<StockHolder>();
		Document doc;
		try {
			String url = "http://f10.eastmoney.com/f10_v2/ShareholderResearch.aspx?code="
					+ StockUtil.getFullStockCode(stockCode);
			doc = Jsoup.connect(url).timeout(0).get();
			Elements items = doc.select("#TTCS_Table_Div").select("table").select("tbody").select("tr");
			for (int i = 0; i <= 10; i++) {
				if (items.isEmpty()) {
					continue;
				}
				Element item = items.get(i);
				Elements tds = item.select("td");
				if (tds.size() >= 5) {
					try {
						StockHolder stockHolder = new StockHolder();
						stockHolder.setStockCode(stockCode);
						stockHolder.setStockholderName(tds.get(0).html());
						stockHolder.setStockholderType(tds.get(1).html());
						stockHolder.setStockholderRatio(Float.parseFloat(tds.get(4).html().replace("%", "")));
						stockHolder.setAddOrSubtract(tds.get(5).html());
						stockholders.add(stockHolder);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stockholders;
	}

	public static List<StockNotice> getStockNoticeList(int pageIndex) {
		List<StockNotice> stockNotices = new ArrayList<StockNotice>();
		Document doc;
		try {
			String url = "http://data.eastmoney.com/Notice/Noticelist.aspx?type=7&market=sh_a&date=&page="+pageIndex;
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
				stockNotices.add(stockNotice);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stockNotices;

	}
	public static void main(String[] args) {
		LongHuBanDao longHuBanGeGuDao = new LongHuBanDao();
		/*
		 * List<LongHuBanJosn> longHuBanJosns = getLongHuBan().getData(); for
		 * (LongHuBanJosn longHuBanJosn : longHuBanJosns) { String salesCode =
		 * longHuBanJosn.getSalesCode(); LongHuBanGeGuResultJosn
		 * banGeGuResultJosn = getGeGuByLhbId(salesCode);
		 * 
		 * longHuBanGeGuDao.addLongHuBanGeGuList(banGeGuResultJosn.getData()); }
		 */
		/*
		 * List<String> stockCodeList = DataTools.getStockCodeList();
		 * stockCodeList.clear(); stockCodeList.add("000786");
		 * stockCodeList.add("002181"); stockCodeList.add("600891");
		 * stockCodeList.add("600976"); for (String stockCode : stockCodeList) {
		 * longHuBanGeGuDao.addTiCaiDetailList(getHeXinTiCai(stockCode)); }
		 * System.out.println("StockInfoUtil.main()");
		 */
		// longHuBanGeGuDao.addLongHuBanList(getLongHuBan().getData());
		// longHuBanGeGuDao.addLongHuBanGeGuList(getLongHuBan());
		// longHuBanGeGuDao.addTiCaiDetailList(getHeXinTiCai("300131"));
		// System.out.println(JsonUtil.toJosn(getHeXinTiCai("300131")));
		/*
		 * List<String> provinceCodeList = DataTools.getProvinceCodeList(); for
		 * (String provinceCode : provinceCodeList) {
		 * longHuBanGeGuDao.addYingYeBuList(getYingYeBuListByProvinceCode(
		 * provinceCode).getData()); }
		 */
/*
		List<String> stockCodeList = DataTools.getStockCodeList();
		stockCodeList.clear();
		stockCodeList.add("002798");
		stockCodeList.add("002800");
		stockCodeList.add("300512");
		stockCodeList.add("300513");
		stockCodeList.add("300516");
		stockCodeList.add("603737");
		for(String stockCode : stockCodeList) {

			List<TiCaiDetail> tiCaiDetails = getHeXinTiCai(stockCode);
			longHuBanGeGuDao.addTiCaiDetailList(tiCaiDetails);
		}*/
		for (int i = 464; i <= 563; i++) {
			List<StockNotice> stockNoticeList = getStockNoticeList(i);
			longHuBanGeGuDao.addStockNoticeList(stockNoticeList);
		}
		
	}

}
