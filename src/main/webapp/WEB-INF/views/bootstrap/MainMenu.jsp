<%@page import="java.util.Date"%>
<%@page import="com.lzj.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="sidebar-left" class="span2">
	<div class="nav-collapse sidebar-nav">
		<ul class="nav nav-tabs nav-stacked main-menu">
			<!-- <li><a href="home.jsp"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 看板监控</span></a></li> -->
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 辅助归类</span><span
					class="label label-important"> 5 </span></a>
				<ul>
					<li><a href="/fz/hushenagu"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 沪深A股</span></a></li>
					<li><a href="/fz/hushenagu_lishi?queryDate=<%=DateUtil.formatDate(new Date(), "yyyy-MM-dd")%>"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 沪深A股历史</span></a></li>
					<li><a href="/fz/hushenbk_lishi?queryDate=<%=DateUtil.formatDate(new Date(), "yyyy-MM-dd")%>"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 沪深版块历史</span></a></li>		
					<li><a href="/fz/bkduibi_line?bkType=8809"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 概念版块对比</span></a></li>	
					<li><a href="/fz/zsduibi_line?bkType=8888"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 指数版块对比</span></a></li>
					<!-- <li><a href="/bootstrap/geguliandong.jsp"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 个股联动</span></a></li>
					<li><a href="/bootstrap/gegugainian.jsp"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 个股概念</span></a></li> -->
					<li><a href="/fz/zhangdiefufenbu"><i
							class="icon-bar-chart"></i><span class="hidden-tablet">
								涨跌幅分布</span></a></li>
					<!-- <li><a href="/bootstrap/jujizhangtingban.jsp"><i
							class="icon-bar-chart"></i><span class="hidden-tablet">
								狙击涨停板</span></a></li> -->
					<!-- <li><a href="/stock/RongQuanServlet"><i
							class="icon-bar-chart"></i><span class="hidden-tablet">
								融券分析</span></a></li> -->
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 报表统计</span><span
					class="label label-important"> 5 </span></a>
				<ul>
					<li><a href="/bbtj/dangriyingkui"><i class="icon-bar-chart"></i><span
							class="hidden-tablet"> 当日盈亏</span></a></li>
					<li><a href="/bbtj/dangriyingkui_chart"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 盈亏曲线</span></a></li>
					<li><a href="/bbtj/zongzichan_chart"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 总资产</span></a></li>
					<li><a href="/bbtj/dangriyingkui_dapan_chart"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 盈亏-大盘曲线</span></a></li>
					<!-- <li><a href="/bootstrap/rishouru_chart.jsp"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 日收入曲线</span></a></li> -->
					<li><a href="/bbtj/rijunshouru_chart"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 日均收入曲线</span></a></li>
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 看多指标</span><span
					class="label label-important"> 4 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/zhangtingbanhuicai.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								涨停板回踩</span></a></li>
					<li><a class="submenu" href="/bootstrap/junxianduotoupailie.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线多头排列</span></a></li>
					<!-- <li><a class="submenu" href="/bootstrap/hongsanbing.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet"> 红三兵</span></a></li>
					<li><a class="submenu" href="/bootstrap/zaochenzhixing.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								早晨之星</span></a></li>
					<li><a class="submenu" href="/bootstrap/chuantoupojiao.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								穿头破脚</span></a></li>
					<li><a class="submenu" href="/bootstrap/jinzhentandi.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								金针探底</span></a></li>
					<li><a class="submenu" href="/bootstrap/xiangtitupo.jsp"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								箱体突破</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								量增价涨</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								MACD*</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								KDJ*</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								BBI*</span></a></li> -->
				</ul></li>
			<!-- <li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 行业资金出入</span><span
					class="label label-important"> 4 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/hangyezijinchuru.jsp?num=50"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前50</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyezijinchuru.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前100</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyezijinchuru.jsp?num=150"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前150</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyezijinchuru.jsp?num=200"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前200</span></a></li>
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 行业龙虎榜</span><span
					class="label label-important"> 4 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/hangyelonghuban.jsp?num=50"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前50</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyelonghuban.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前100</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyelonghuban.jsp?num=150"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前150</span></a></li>
					<li><a class="submenu" href="/bootstrap/hangyelonghuban.jsp?num=200"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前200</span></a></li>
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 板块龙虎榜</span><span
					class="label label-important"> 4 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/bankuai.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前20</span></a></li>
					<li><a class="submenu" href="/bootstrap/bankuai.jsp?num=50"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前50</span></a></li>
					<li><a class="submenu" href="/bootstrap/bankuai.jsp?num=80"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前80</span></a></li>
					<li><a class="submenu" href="/bootstrap/bankuai.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前100</span></a></li>
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 流通盘龙虎榜</span><span
					class="label label-important"> 4 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前20</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=50"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前50</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=80"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前80</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								领先前100</span></a></li>
				</ul></li>
			<li><a class="dropmenu" href="#"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> 均线博弈</span><span
					class="label label-important"> 7 </span></a>
				<ul>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=20"><i
							class="icon-file-alt"></i><span class="hidden-tablet"> 均线5</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=50"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线10</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=80"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线20</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线30</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线60</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线120</span></a></li>
					<li><a class="submenu" href="/bootstrap/liutongpan.jsp?num=100"><i
							class="icon-file-alt"></i><span class="hidden-tablet">
								均线250</span></a></li>
				</ul></li>

			<li><a href="zhangdiefufenbu.jsp"><i class="icon-bar-chart"></i><span
					class="hidden-tablet"> 系统配置</span></a></li>
			<li><a href="/bootstrap/chart.html"><i class="icon-list-alt"></i><span
					class="hidden-tablet"> Charts</span></a></li> -->
		</ul>
	</div>
</div>