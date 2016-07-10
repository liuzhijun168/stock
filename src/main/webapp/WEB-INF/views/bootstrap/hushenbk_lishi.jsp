<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lzj.DBTools"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

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
<link id="bootstrap-style" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<link id="base-style" href="${pageContext.request.contextPath}/bootstrap/css/style.css" rel="stylesheet">
<link id="base-style-responsive" href="${pageContext.request.contextPath}/bootstrap/css/style-responsive.css"
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/bootstrap/img/favicon.ico">
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
						<a href="#"> 沪深A股</a>
					</li>
			</ul>

		<div class="row-fluid sortable">		
				<div class="box span12">
				     <a class="btn btn-primary preImg" onclick="goByDate('${preDate}')"> 上一页</a>
				     <a class="btn btn-primary preImg" onclick="goByDate('${nextDate}')"> 下一页</a>
					<div class="box-content">
						<table class="table table-striped table-bordered bootstrap-datatable datatable">
						  <thead>
							  <tr>
								  <th width="6%">代码</th>
								  <th width="8%">名称</th>
								  <th width="4%">涨%</th>
								  <th width="4%">振%</th>
								  <th width="4%">BBI%</th>
								  <th width="8%">回踩</th>
								  <th width="6%">备注</th>
								  <th width="6%">操作</th>
								  <th width="12%">更新日期</th>
								 <!--  <th>Status</th>
								  <th>Actions</th> -->
							  </tr>
						  </thead>   
						  <tbody>
						
						   <c:forEach items="${stockBkDataDays}" var="stockBkDataDay" varStatus="index" >
					            <tr>
								<td id="${index }">${stockBkDataDay.b }</td>
								<td>${stockBkDataDay.c }</td>
								<td>${stockBkDataDay.e }</td>
								<td><%-- ${stockBkDataDay.t } --%></td>
								<td><%-- ${stockBkDataDay.bbibais } --%></td>
								<td><%-- <%=q<m5?"m5;":"" %><%=q<m10?"m10;":"" %><%=q<m20?"m20;":"" %><%=d>=bbi?"bbi;":"" %> --%></td>
								<td><%-- <%=b.startsWith("6")?"沪A;":"" %><%=b.startsWith("000")?"深A;":"" %><%=b.startsWith("002")?"中小板;":"" %><%=b.startsWith("300")?"创业板;":"" %> --%></td>
								<td>
								</td>
								<td>${stockBkDataDay.createDate }</td>
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
					        </c:forEach>
						
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
	<script type="text/javascript">
	$('.preImg').click(function(e){
		var curIdx = $("#curIdx").val();
		var preIdx = parseInt(curIdx)-1;
		var curCode = $("table:eq(0) tr:eq("+preIdx+") td:eq(0)").html();
		if(curCode){
			$("#curIdx").val(preIdx);
			$("#curCode").val(curCode);
			show();
		}
		
	});
	$('.nextImg').click(function(e){
		var curIdx = $("#curIdx").val();
		var nextIdx = parseInt(curIdx)+2;
		var curCode = $("table:eq(0) tr:eq("+nextIdx+") td:eq(0)").html();
		if(curCode){
			$("#curIdx").val(nextIdx);
			$("#curCode").val(curCode);
			show();
		}
		
	});
	$('.hImg').click(function(e){
		$("#zoushiCyc").val("min");
		show();
	}); 
	$('.dImg').click(function(e){
		$("#zoushiCyc").val("daily");
		show();
	});
	$('.wImg').click(function(e){
		$("#zoushiCyc").val("weekly");
		show();
	});
	$('.mImg').click(function(e){
		$("#zoushiCyc").val("monthly");
		show();
	});
	$('.zoushiImg').click(function(e){
		e = e ? e : window.event;
		var obj = e.srcElement ? e.srcElement : e.target;
		$("#curIdx").val($(obj).parents("tr").index());
		$("#curCode").val($(obj).attr("code"));
		$("#zoushiCyc").val("min");
		e.preventDefault();
		show();
	});
	function show(){
		//var code =;
		var curCode = $("#curCode").val();
		var zoushiCyc = $("#zoushiCyc").val();
		var fdStart = curCode.indexOf("6");
		if(fdStart == 0){
			curCode = "sh"+curCode;
		}else{
			curCode = "sz"+curCode;   
		}
		$("#stockImg").attr("src","http://image.sinajs.cn/newchart/"+zoushiCyc+"/n/"+curCode+".gif"); 
		$('#myModal').modal('show');
	}
	
	function goByDate(goDate){
		 window.location.href="${pageContext.request.contextPath}/fz/hushenbk_lishi?queryDate="+goDate; 
	}
	$('.datatable').dataTable({
		"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
		"sPaginationType": "bootstrap",
		"iDisplayLength": 100,
		"aaSorting": [[2, "desc"]],
		"oLanguage": {
		"sLengthMenu": "_MENU_ records per page"
		}
	} );
	</script>
	<!-- end: JavaScript-->
	
</body>
</html>
