<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lzj.DBTools"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<% 
	String sql = "select * from gainian order by id";
	ResultSet resultSet = DBTools.getResult(sql);
	
%>

<!-- start: Meta -->
<meta charset="utf-8">
<title>证券交易管理平台</title>
<meta name="description" content="Bootstrap Metro Dashboard">
<meta name="author" content="Dennis Ji">
<meta name="keyword"
	content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<!-- end: Meta -->

<!-- start: Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- end: Mobile Specific -->

<!-- start: CSS -->
<link id="bootstrap-style" href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="css/style-responsive.css"
	rel="stylesheet">
<!-- end: CSS -->


<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	  	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<link id="ie-style" href="css/ie.css" rel="stylesheet">
	<![endif]-->

<!--[if IE 9]>
		<link id="ie9style" href="css/ie9.css" rel="stylesheet">
	<![endif]-->

<!-- start: Favicon -->
<link rel="shortcut icon" href="img/favicon.ico">
<!-- end: Favicon -->

</head>

<body>
	<!-- start: Header -->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- start: Header -->

	<div class="container-fluid-full">
		<div class="row-fluid">

			<!-- start: Main Menu -->
			<jsp:include page="MainMenu.jsp"></jsp:include>
			<!-- end: Main Menu -->

			<noscript>
				<div class="alert alert-block span10">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<!-- start: Content -->
			<div id="content" class="span10">


				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.html">Home</a> <i
						class="icon-angle-right"></i></li>
					<li>
					<li>
						<a href="#"> 个股联动</a>
					</li>
			</ul>

		<div class="row-fluid sortable">		
				<div class="box span12">
					<!-- <div class="box-header" data-original-title>
						<h2><i class="halflings-icon user"></i><span class="break"></span>Members</h2>
						<div class="box-icon">
							<a href="#" class="btn-setting"><i class="halflings-icon wrench"></i></a>
							<a href="#" class="btn-minimize"><i class="halflings-icon chevron-up"></i></a>
							<a href="#" class="btn-close"><i class="halflings-icon remove"></i></a>
						</div>
					</div> -->
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="4%">序列</th>
								  <th width="8%">名称</th>
								  <th width="60%">相关股</th>
								 <!--  <th>Status</th>
								  <th>Actions</th> -->
							  </tr>
						  </thead>   
						  <tbody>
						   <%
						   	  int index = 0;
							  while(resultSet.next()){
								  String gnid = resultSet.getString("id");
								 
								  String createDate = resultSet.getString("create_date").substring(5);
								  %>
								<tr>
								<td><%=resultSet.getString("id")%></td>
								<td><%=resultSet.getString("name")%></td>
								<td>
								<%
								  sql = "select * from gainiangu,stock_data_query sdq where gnid='"+gnid+"' and sdq.b=gainiangu.code order by e desc";
								  ResultSet subResultSet = DBTools.getResult(sql);
								  while(subResultSet.next()){
									  String stockCode = subResultSet.getString("code");
									  String stockName = subResultSet.getString("c");
									  //e涨幅
									  String e = subResultSet.getString("e");
									  %>
									  <%=stockName+"("+e+");"%>
									  <%
								  }
								%>
								</td>
								<!--<td class="center">
									<span class="label label-success">Active</span>
								</td>
								 <td class="center">
									<a class="btn btn-success" href="#">
										<i class="halflings-icon white zoom-in"></i>  
									</a>
									<a class="btn btn-info" href="#">
										<i class="halflings-icon white edit"></i>  
									</a>
									<a class="btn btn-danger" href="#">
										<i class="halflings-icon white trash"></i> 
									</a>
								</td> -->
							</tr>
								  <% 
							  }
						   %>
						  </tbody>
					  </table>            
					</div>
				</div><!--/span-->
			</div><!--/row-->
		</div>
		<!--/.fluid-container-->
	
			<!-- end: Content -->
		</div>
		<!--/#content.span10-->
		</div>
	<!--/fluid-row-->
		
	<div class="modal hide fade" id="myModal">
		<input id="curCode" type="hidden" >
		<input id="zoushiCyc" type="hidden" >
		<input id="curIdx" type="hidden" >
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>走势图</h3>
		</div>
		<div class="modal-body">
			<p><img id="stockImg" src="http://image.sinajs.cn/newchart/daily/n/sh000001.gif"></p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn btn-primary preImg">上一个</a>
			<a href="#" class="btn btn-primary nextImg">下一个</a>
			<a href="#" class="btn btn-primary hImg">分时</a>
			<a href="#" class="btn btn-primary dImg">日</a>
			<a href="#" class="btn btn-primary wImg">周</a>
			<a href="#" class="btn btn-primary mImg">月</a>
			<!-- <a href="#" class="btn" data-dismiss="modal">Close</a>
			<a href="#" class="btn btn-primary">Save changes</a> -->
		</div>
	</div>
	
	<div class="clearfix"></div>
	
	<footer>

		<p>
			<span style="text-align:left;float:left">&copy; 2013 <a
			href="http://jiji262.github.io/Bootstrap_Metro_Dashboard/"
			alt="Bootstrap_Metro_Dashboard">Bootstrap Metro Dashboard</a></span>
		</p>

	</footer>
	
	<!-- start: JavaScript-->
	<jsp:include page="Js.jsp"></jsp:include>
	<!-- end: JavaScript-->
	
</body>
</html>
