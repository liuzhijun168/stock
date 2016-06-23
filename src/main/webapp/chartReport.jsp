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
    <title>图表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width">
    <script type="text/javascript" src="/stock/humble/demos/yepnope.js"></script>
    <link rel="stylesheet" href="/stock/humble/demos/demos.css" type="text/css" />
    <link rel="stylesheet" href="/stock/humble/css/flotr.css" type="text/css" />
    <link rel="stylesheet" href="/stock/humble/css/templates/finance.css" type="text/css" />
</head>

<body>
  <div id="content">
    <h3>Ajax Demo:</h3>
    <div id="demo"></div>
    <div id="footer">
      &copy; 2012 <a href="http://www.humblesoftware.coms/">humble software development</a>
    </div>
    
  </div>

  <script type="text/javascript" src="/stock/humble/demos/ajax.js"></script>
  <script type="text/javascript" src="/stock/humble/demos/includes.js"></script>

</body>

</html>
