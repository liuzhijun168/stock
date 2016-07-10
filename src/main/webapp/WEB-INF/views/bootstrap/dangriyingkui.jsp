<%@page import="com.lzj.eastmoney.http.StockInfoUtil"%>
<%@page import="com.lzj.util.DateUtil"%>
<%@page import="com.lzj.Report"%>
<%@page import="java.util.List"%>
<%@page import="com.lzj.DataTools"%>
<%@page import="com.lzj.DBTools"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<%
	List<Report> reports = DataTools.getReportData();
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico">
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
					<li><a href="#">报表统计</a> <i class="icon-angle-right"></i></li>
					<li><a href="#"> 当日盈亏</a></li>
				</ul>


				<div class="row-fluid sortable">
					<div class="box span12">
						<div class="box-header" data-original-title>
							<h2>
								<i id="addData">添加数据</i><span class="break"></span>
								<i id="addZiJinData">资金变更</i><span class="break"></span>
							</h2>
						</div>
					</div>
					-
					<div class="box-content">
						<table
							class="table table-striped table-bordered bootstrap-datatable datatable">
							<thead>
								<tr>
									<th width="4%">序</th>
									<th width="8%">日期</th>
									<th width="7%">沪指</th>
									<th width="6%">日幅%</th>
									<th width="6%">周幅%</th>
									<th width="6%">累积幅%</th>
									<th width="6%">市值</th>
									<th width="6%">仓位%</th>
									<th width="6%">日盈亏</th>
									<th width="6%">日盈亏%</th>
									<th width="6%">周盈亏</th>
									<th width="6%">周盈亏%</th>
									<th width="6%">月盈亏</th>
									<th width="8%">月盈亏%</th>
									<th width="8%">年盈亏</th>
									<th width="8%">年盈亏%</th>
									<th width="8%">总盈亏</th>
									<th width="8%">日均盈亏</th>
									<th width="8%">操作</th>
								</tr>
							</thead>
							<tbody>
								<%
									int indexInt = 0;
									String indexStr = "";
									for (int i = 0; i < reports.size(); i++) {
										Report report = reports.get(i);
										indexInt = (reports.size() - i);
										if (indexInt < 10) {
											indexStr = "0" + indexInt;
										} else {
											indexStr = "" + indexInt;
										}
								%>
								<tr>
									<td><%=indexStr%></td>
									<td><%=report.isMd() ? "<b><i>" + report.getCreateDate() + "</i></b>" : report.getCreateDate()%></td>
									<td><%=report.getSzzs()%></td>
									<td><%=DataTools.setColor(2, report.getSzzsbili())%></td>
									<td><%=DataTools.setColor(2, report.getSzzsbili_w())%></td>
									<td><%=DataTools.setColor(2, report.getSzzsbili_t())%></td>
									<td><%=DataTools.setColor(0, report.getChenben())%></td>
									<td><%=DataTools.setColor(0, report.getCangwei())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_d())%></td>
									<td><%=DataTools.setColor(2, report.getFudongkuiyinbili_d())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_w())%></td>
									<td><%=DataTools.setColor(2, report.getFudongkuiyinbili_w())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_m())%></td>
									<td><%=DataTools.setColor(2, report.getFudongkuiyinbili_m())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_y())%></td>
									<td><%=DataTools.setColor(2, report.getFudongkuiyinbili_y())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_t())%></td>
									<td><%=DataTools.setColor(0, report.getFudongkuiyin_t() / (reports.size() - indexInt + 1))%></td>
									<td><button onclick="del(<%=report.getId()%>)"
											type="button" class="btn btn-primary">删除</button></td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
		</div>
		<!--/.fluid-container-->

		<!-- end: Content -->
	</div>
	<!--/#content.span10-->
	</div>
	<!--/fluid-row-->

	<div class="modal hide fade" id="myModal">
		<input id="curCode" type="hidden"> <input id="zoushiCyc"
			type="hidden"> <input id="curIdx" type="hidden">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>添加数据</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/bbtj/addBlotter">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="typeahead">上证指数 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" name="szzs"
								value="<%=StockInfoUtil.getStockDetail("sh000001").getPrice()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">净资产 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" name="balance"
								value="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">总资产 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" name="balanceYy"
								value="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="date01">日期</label>
						<div class="controls">
							<input type="text" class="input-xlarge datepicker"
								name="createDate" value="<%=DateUtil.formatDate("yyyy/MM/dd")%>">
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="modal hide fade" id="myZiJinModal">
		<input id="curCode" type="hidden"> <input id="zoushiCyc"
			type="hidden"> <input id="curIdx" type="hidden">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>资金变更</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath}/balance/addBalance">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="typeahead">变动类型 </label>
						<div class="controls">
							<select name="changeType">
							  <option value="1">存入</option>
							  <option value="-1">取出</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">变动金额 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" name="balance"
								value="" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="typeahead">备注 </label>
						<div class="controls">
							<input type="text" class="span6 typeahead" name="remark"
								value="" />
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="clearfix"></div>

	<footer>

	<p>
		<span style="text-align: left; float: left">&copy; 2013 <a
			href="http://jiji262.github.io/Bootstrap_Metro_Dashboard/"
			alt="Bootstrap_Metro_Dashboard">Bootstrap Metro Dashboard</a></span>

	</p>

	</footer>

	<!-- start: JavaScript-->
	<jsp:include page="Js.jsp"></jsp:include>
	<script type="text/javascript">
		$('#addData').click(function(e) {
			$('#myModal').modal('show').css({
                width: 'auto',
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });;
		});
		$('#addZiJinData').click(function(e) {
			$('#myZiJinModal').modal('show').css({
                width: 'auto',
                'margin-left': function () {
                    return -($(this).width() / 2);
                }
            });;
		});
		
		function del(blotterId){
			 window.location.href="${pageContext.request.contextPath}/bbtj/delBlotter?blotterId="+blotterId; 
		}
		$('.datatable').dataTable({
			"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span12'i><'span12 center'p>>",
			"sPaginationType": "bootstrap",
			"iDisplayLength": 100,
			"oLanguage": {
			"sLengthMenu": "_MENU_ records per page"
			}
		} );
	</script>
	<!-- end: JavaScript-->

</body>
</html>
