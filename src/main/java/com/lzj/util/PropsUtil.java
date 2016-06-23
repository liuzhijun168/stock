package com.lzj.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropsUtil {
	// 属性文件一般放到把db.properties放在系统属性java.home下，一般是jdk目录下的 jre目录里。
	private static final String javaHome = System.getProperty("java.home");
	private static final String FS = System.getProperty("file.separator");
	private static final String propsFileName = javaHome + FS + "destinations.properties";

	/**
	 * 构造函数 找到数据源，并用这个数据源创建连接
	 */
	public PropsUtil() {
	}

	public String getPropsFilePath() {
		String filePath = this.getClass().getResource("/").getPath().toString();
		filePath = filePath.substring(0, filePath.indexOf("classes") - 1) + "/dbconfig.properties";
		return filePath;
	}

	public InputStream getPropsIS() {
		InputStream ins = this.getClass().getResourceAsStream("/dbconfig.properties");
		return ins;
	}

	/**
	 * 读取属性文件中的属性值
	 * 
	 * @param attr
	 * @return
	 */
	public String get(String attr) {
		String retValue = "";
		Properties props = new Properties();
		try {
			/*
			 * if (!FileUtil.isFileExist(getPropsFilePath())) { return ""; }
			 * FileInputStream fi = new FileInputStream(getPropsFilePath());
			 */
			InputStream fi = getPropsIS();
			props.load(fi);
			fi.close();
			retValue = props.getProperty(attr);
		} catch (Exception e) {
			return "";
		}
		return retValue;
	}

	/**
	 * 读取属性文件中的属性值
	 * 
	 * @return
	 */
	public HashMap readAllProps() {
		HashMap h = new HashMap();
		Properties props = new Properties();
		try {
			/*
			 * if (!FileUtil.isFileExist(getPropsFilePath())) return new
			 * HashMap(); FileInputStream fi = new
			 * FileInputStream(getPropsFilePath());
			 */
			InputStream fi = getPropsIS();
			props.load(fi);
			fi.close();
			Enumeration er = props.propertyNames();
			while (er.hasMoreElements()) {
				String paramName = (String) er.nextElement();
				h.put(paramName, props.getProperty(paramName));
			}
		} catch (Exception e) {
			return new HashMap();
		}
		return h;
	}
}