package com.lzj.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

	

	/**

	* 从json字符串中解析出java对象

	* @author:qiuchen

	* @createTime:2012-7-8

	* @param jsonStr json格式字符串

	* @param clazz java类

	* @return clazz的实例

	*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object parseJavaObject(String jsonStr,Class clazz){

		return JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);

	}

    /** 
     * 将JavaBean转换为Json格式的数据/Json文本 
     * 用户数据的传递：例如上传服务器 
     */  
	public static String toJosn(Object obj) {  
        String josn = JSON.toJSONString(obj);  
        return josn;
    }  
	

}