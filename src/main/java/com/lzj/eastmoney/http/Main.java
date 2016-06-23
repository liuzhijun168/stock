package com.lzj.eastmoney.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {
	
	
	public static void main(String[] args) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(
					"http://f10.eastmoney.com/f10_v2/CoreConception.aspx?code=sz002782&timetip=635994723137473541");
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
			BufferedReader br = new BufferedReader(new InputStreamReader(inSm,"UTF-8"));
			Scanner inScn = new Scanner(br);
			while (inScn.hasNextLine()) {
				String str = inScn.nextLine();
				System.out.println(str);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
