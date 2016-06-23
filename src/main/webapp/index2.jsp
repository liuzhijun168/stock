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
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Charts</title>
<link rel="stylesheet" type="text/css" href="ext-3.2.0/resources/css/ext-all.css" />

    <!-- GC -->
 	<!-- LIBS -->
 	<script type="text/javascript" src="ext-3.2.0/adapter/ext/ext-base.js"></script>
 	<!-- ENDLIBS -->

    <script type="text/javascript" src="ext-3.2.0/ext-all.js"></script>

    <script type="text/javascript" src="ext-3.2.0/examples/chart/charts1.js"></script>

    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="ext-3.2.0/examples/shared/examples.css" />

    <style type="text/css">
    #container {
        padding:10px;
    }
    #container .x-panel {
        margin:10px;
    }
    #container .x-panel-ml {
        padding-left:1px;
    }
    #container .x-panel-mr {
        padding-right:1px;
    }
    #container .x-panel-bl {
        padding-left:2px;
    }

    #container .x-panel-br {
        padding-right:2px;
    }
    #container .x-panel-body {

    }
    #container .x-panel-mc {
        padding-top:0;
    }
    #container .x-panel-bc .x-panel-footer {
        padding-bottom:2px;
    }
    #container .x-panel-nofooter .x-panel-bc {
        height:2px;
    }
    #container .x-toolbar {
        border:1px solid #99BBE8;
        border-width: 0 0 1px 0;
    }
    .chart {
        background-image: url(ext-3.2.0/examples/chart/chart.gif) !important;
    }
    </style>
</head>
<body>
<script type="text/javascript" src="ext-3.2.0/examples/shared/examples.js"></script><!-- EXAMPLES -->
<h1>Charts</h1>
<p>The js is not minified so it is readable. See <a href="charts.js">charts.js</a>.</p>

<div id="container">
    
</div>

</body>
</html>
