package stock.stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lzj.eastmoney.http.StockInfoUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		System.out.println(StockInfoUtil.getGeGuByLhbId("1"));
	}
	
	
	/**
	 * Rigourous Test :-)
	 */
	public void testGetAllLongHuBan() {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(
					"http://data.eastmoney.com/DataCenter_V3/stock2016/TraderStatistic/pagesize=0,page=1,sortRule=-1,sortType=,startDate=2016-02-22,endDate=2016-05-22,gpfw=0,js=var%20data_tab_1.html?rt=24397550");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			/*
			 * if (entity != null) {
			 * System.out.println("Response content length: " +
			 * entity.getContentLength()); }
			 */
			// char a = '10';
			System.out.println();
			InputStream inSm = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(inSm,"GBK"));
			Scanner inScn = new Scanner(br);
			while (inScn.hasNextLine()) {
				String str = inScn.nextLine();
				System.out.println(str.replace("var data_tab_1=", ""));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
