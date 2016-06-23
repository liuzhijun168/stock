package com.lzj.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.lzj.eastmoney.dao.LongHuBanDao;
import com.lzj.eastmoney.data.josn.LongHuBanResultJosn;
import com.lzj.eastmoney.data.josn.TiCaiDetail;

public class HttpUtil
{
   
    public static String getJsonContent(String urlStr)
    {
        try
        {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url
                    .openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200)
            {
                return convertStream2Json(httpConn.getInputStream());
            }
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

   
    private static String convertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray(),"GBK");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
    public static void main(String[] args) {
		/*String data = getJsonContent("http://data.eastmoney.com/DataCenter_V3/stock2016/TraderStatistic/pagesize=5,page=1,sortRule=-1,sortType=,startDate=2016-02-22,endDate=2016-05-22,gpfw=0,js=var%20data_tab_1.html?rt=24397550");
		//var data_tab_1={"success":true,"pages":0,"data":[],"url":"http://datainterface3.eastmoney.com//EM_DataCenter_V3/api/LHBYYBMMTJ/GetLHBYYBMMTJ?tkn=eastmoney&salesCode=&dayNum=&startDateTime=2016-02-22&endDateTime=2016-05-22&sortfield=&sortdirec=1&pageNum=1&pageSize=0&cfg=yybmmtj"}
		data = data.replace("var data_tab_1=", "");
		System.out.println(data);
		LongHuBanResultJosn longHuBanResultJosn = JSON.parseObject(data, LongHuBanResultJosn.class);
		System.out.println(longHuBanResultJosn);*/
    	LongHuBanDao banDao = new LongHuBanDao();
    	List<TiCaiDetail> tiCaiDetails = null;
		banDao.addTiCaiDetailList(tiCaiDetails);
		

    }
}