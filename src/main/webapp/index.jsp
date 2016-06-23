<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>金融投资管理系统</title>
		<script type="text/javascript" src="resources/js/dump.js"></script>
		<!--导入Ext-->
		<script type="text/javascript" src="ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="ext-3.2.0/ext-all-debug.js"></script>
		<script type="text/javascript" src="ext-3.2.0/ext-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="ext-3.2.0/resources/css/ext-all.css" />
		<!-- 自定义内容 -->
		<link rel="stylesheet" type="text/css" href="resources/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="MainContainer.css" />
		<!-- 客户管理模块 -->
		<link rel="stylesheet" type="text/css" href="usermng/resources/css/UserMng.css" />
		<script type="text/javascript" src="usermng/UserTree.js"></script>
		<script type="text/javascript" src="usermng/UserGrid.js"></script>
		<script type="text/javascript" src="usermng/UserWeekGrid.js"></script>
		<script type="text/javascript" src="usermng/UserDetailWindow.js"></script>
		<script type="text/javascript" src="usermng/UserMngPanel.js"></script>
		<!-- 产品管理模块 -->
		<script type="text/javascript" src="productmng/ProductTree.js"></script>
		<script type="text/javascript" src="productmng/ProductMngPanel.js"></script>
		<!-- 图表管理模块 -->
		<script type="text/javascript" src="chartmng/ChartTree.js"></script>
		<script type="text/javascript" src="chartmng/ChartMngPanel.js"></script>
		
		<script type="text/javascript" src="MainContainer.js"></script>
		<script type="text/javascript" src="/stock/ext-3.2.0/examples/ux/fileuploadfield/FileUploadField.js"></script>
	</head>
	<body>
	</body>
</html>