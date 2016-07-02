<%@page import="com.lzj.ChiGu"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lzj.DataTools"%>
<%@page import="com.lzj.ChiGuDetail"%>
<%@page import="com.lzj.DBTools"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<style type="text/css">

/* Star Rating
=================================================================== */

.rating1 {
  unicode-bidi: bidi-override;
  direction: ltr;
  font-size: 20px;
}
.rating1 span.star1,
.rating1 span.star1 {
  font-family: FontAwesome;
  font-weight: normal;
  font-style: normal;
  display: inline-block;
}
.rating1 span.star2,
.rating1 span.star2 {
  font-family: FontAwesome;
  font-weight: normal;
  font-style: normal;
  display: inline-block;
}

.rating1 span.star1:before,
.rating1 span.star1:before {
  content: "\f006";
  padding-right: 5px;
  color: #999999;
}

.rating1 span.star2:before,
.rating1 span.star2:before {
  content: "\f005";
  padding-right: 5px;
  color: #e3cf7a;
}

</style>





<%
	String sql = "select count(*)  from stock_data_query where e>0 and b like '6%'";
	String shzhang = DBTools.getString(sql);
	sql = "	select count(*)  from stock_data_query where e>0 and (b like '0%' or b like '3%')";
	String szzhang = DBTools.getString(sql);
	sql = "	select count(*)  from stock_data_query where e>0 and b like '002%'";
	String zhongxiaoban = DBTools.getString(sql);
	sql = "	select count(*)  from stock_data_query where e>0 and b like '300%'";
	String chuangyeban = DBTools.getString(sql);
	sql = "	select count(*)  from stock_data_query where e>0 ";
	String hushena = DBTools.getString(sql);
	String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
	ChiGuDetail chiGuDetail = DataTools.getChiGuData(date);
	if(chiGuDetail == null){
		return;
	}
	int zongchicang = (int) (chiGuDetail.getCankaoshizhi()/ chiGuDetail.getZichang() * 100);
	List<ChiGu> chiguList = chiGuDetail.getChiguList();
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
<!-- <link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
	rel='stylesheet' type='text/css'> -->
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
					<li><i class="icon-eye-open"></i><a href="#"> 看板监控</a></li>
				</ul>

				<div class="row-fluid">
					<div class="span3 statbox red" onTablet="span6" onDesktop="span3">
						<div class="boxchart">1,2,6,4,0,8,2,4,5,3,1,7,5</div>
						<div class="number">
							<%=shzhang%><i class="icon-arrow-up"></i>
						</div>
						<div class="title"><%=String.format("%.2f", Float.parseFloat(shzhang) / 98.8)%></div>
						<div class="footer">
							<a href="#"> 沪A</a>
						</div>
					</div>
					<div class="span3 statbox purple" onTablet="span6"
						onDesktop="span3">
						<div class="boxchart">5,6,7,2,0,4,2,4,8,2,3,3,2</div>
						<div class="number">
							<%=szzhang%><i class="icon-arrow-up"></i>
						</div>
						<div class="title"><%=String.format("%.2f", Float.parseFloat(szzhang) / 160.9)%></div>
						<div class="footer">
							<a href="#"> 深A</a>
						</div>
					</div>
					<div class="span3 statbox blue noMargin" onTablet="span6"
						onDesktop="span3">
						<div class="boxchart">5,6,7,2,0,-4,-2,4,8,2,3,3,2</div>
						<div class="number">
							<%=zhongxiaoban%><i class="icon-arrow-up"></i>
						</div>
						<div class="title"><%=String.format("%.2f",
					Float.parseFloat(zhongxiaoban) / 73.5)%></div>
						<div class="footer">
							<a href="#"> 中小板</a>
						</div>
					</div>
					<div class="span3 statbox yellow" onTablet="span6"
						onDesktop="span3">
						<div class="boxchart">7,2,2,2,1,-4,-2,4,8,,0,3,3,5</div>
						<div class="number">
							<%=chuangyeban%><i class="icon-arrow-up"></i>
							<!-- icon-arrow-down -->
						</div>
						<div class="title"><%=String.format("%.2f",
					Float.parseFloat(chuangyeban) / 40.7)%></div>
						<div class="footer">
							<a href="#"> 创业板</a>
						</div>
					</div>

				</div>

				<div class="row-fluid">
					<div class="box span6">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon list"></i><span class="break"></span>操作参考
							</h2>
						</div>
						<div class="box-content">
							<dl>
								<dt>风险系数</dt>
								<dd>
									<span class="rating1">
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star2"></span>
				                        <span class="star1"></span>
				                        <span class="star1"></span>
				                    </span>
								</dd>
								<dt>账户信息</dt>
								<dd>
									当前仓位：<%=zongchicang%>%
									目标仓位：20%   
									减仓：<%=(zongchicang-20)*chiGuDetail.getCankaoshizhi()/100 %>
								</dd>
								<dt>上证指数</dt>
								<dd>
									MACD：金叉&nbsp;
									KDJ：金叉&nbsp;
									BBI：上&nbsp;
									五日均线：上&nbsp;
								 </dd>
								 <dd>
									压力：3400&nbsp; 
								             支撑：3300,五日线&nbsp;
								             量能：温和放量
								 </dd>
								 <dd style="color: red;">
								 	明天变盘
								    IPO要发
								    3400不过就麻烦大了，多看少动   
								 </dd>
								<dt>个股</dt>
								<dd>大智慧和通达动力可以出</dd>
								<dd>广电网络早盘不强势攻击就要考虑出一半或者出完</dd>
								<dd>高鸿股份不大阳线就要出了</dd>
								<dd>大唐电信可以加仓</dd>
							</dl>
						</div>
						
						
					</div>
					
					<div class="box span6">
						<div class="box-header" data-original-title>
							<h2>
								<i class="halflings-icon list"></i><span class="break"></span>个股持仓(<%=chiguList.size() %>)
							</h2>
						</div>
						<div class="box-content">
							<dl>
								<%
								
								for(int i=0; i < chiguList.size(); i++){
									ChiGu chiGu = chiguList.get(i);
									%>
									<dt><%=chiGu.getName()%>/<%=chiGu.getCode()%></dt>
									<dd>
										仓位：<%=(int) (chiGu.getZuixinshizhi()/ chiGuDetail.getZichang() * 100)%>%
									</dd>
									<%
								}
								%>
							</dl>
						</div>
						
						
					</div>
					<!--/span-->
					<!-- End .sparkStats -->

				</div>

				<div class="row-fluid hideInIE8 circleStats">
					<div class="span6" onTablet="span4" onDesktop="span2">
						 <img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/sh000001.gif"/>
					</div>
					<div class="span6" onTablet="span4" onDesktop="span2">
						 <img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/sz399001.gif"/>
					</div>
				</div>
				<div class="row-fluid hideInIE8 circleStats">
					<div class="span6" onTablet="span4" onDesktop="span2">
						 <img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/sz399005.gif"/>
					</div>
					<div class="span6" onTablet="span4" onDesktop="span2">
						 <img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/sz399006.gif"/>
					</div>
				</div>
				
				<%
					for(int i=0; i < chiguList.size(); i++){
						ChiGu chiGu = chiguList.get(i);
						String stockCode = "";
						if(chiGu.getCode().startsWith("6")){
							stockCode = "sh"+chiGu.getCode();
						}else{
							stockCode = "sz"+chiGu.getCode();
						}
						if(i%2 ==0 ){
							
							;
							%>
							<div class="row-fluid hideInIE8 circleStats">
								<div class="span6" onTablet="span4" onDesktop="span2">
								 	<img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/<%=stockCode %>.gif"/>
								</div>
							<%
						}else{
							%>
								<div class="span6" onTablet="span4" onDesktop="span2">
								    <img class="stockImg" alt="" src="http://image.sinajs.cn/newchart/min/n/<%=stockCode %>.gif"/>
								</div>
								</div>
							<% 
						}
											
					}
					%>
								
				<!--/row-->



			</div>
			<!--/.fluid-container-->

			<!-- end: Content -->
		</div>
		<!--/#content.span10-->
	</div>
	<!--/fluid-row-->

	<div class="modal hide fade" id="myModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Settings</h3>
		</div>
		<div class="modal-body">
			<p>Here settings can be configured...</p>
		</div>
		<div class="modal-footer">
			<a href="#" class="btn" data-dismiss="modal">Close</a> <a href="#"
				class="btn btn-primary">Save changes</a>
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

	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="js/jquery-migrate-1.0.0.min.js"></script>

	<script src="js/jquery-ui-1.10.0.custom.min.js"></script>

	<script src="js/jquery.ui.touch-punch.js"></script>

	<script src="js/modernizr.js"></script>

	<script src="js/bootstrap.min.js"></script>

	<script src="js/jquery.cookie.js"></script>

	<script src='js/fullcalendar.min.js'></script>

	<script src='js/jquery.dataTables.min.js'></script>

	<script src="js/excanvas.js"></script>
	<script src="js/jquery.flot.js"></script>
	<script src="js/jquery.flot.pie.js"></script>
	<script src="js/jquery.flot.stack.js"></script>
	<script src="js/jquery.flot.resize.min.js"></script>

	<script src="js/jquery.chosen.min.js"></script>

	<script src="js/jquery.uniform.min.js"></script>

	<script src="js/jquery.cleditor.min.js"></script>

	<script src="js/jquery.noty.js"></script>

	<script src="js/jquery.elfinder.min.js"></script>

	<script src="js/jquery.raty.min.js"></script>

	<script src="js/jquery.iphone.toggle.js"></script>

	<script src="js/jquery.uploadify-3.1.min.js"></script>

	<script src="js/jquery.gritter.min.js"></script>

	<script src="js/jquery.imagesloaded.js"></script>

	<script src="js/jquery.masonry.min.js"></script>

	<script src="js/jquery.knob.modified.js"></script>

	<script src="js/jquery.sparkline.min.js"></script>

	<script src="js/counter.js"></script>

	<script src="js/retina.js"></script>

	<script src="js/custom.js"></script>
	<script type="text/javascript">
		$(".stockImg").click(function(){
			$(".stockImg").each(function () {
				var stockImgObj = $($(this));
				stockImgObj.attr("src",stockImgObj.attr("src")+"&1"); 
		    });
		});
	</script>
	<!-- end: JavaScript-->

</body>
</html>
