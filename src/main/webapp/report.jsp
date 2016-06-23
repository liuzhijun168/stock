<%@page import="java.text.DecimalFormat"%>
<%@page import="com.lzj.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>股票投资收益报表</title>
</head>
<body>
<table border="1px" cellspacing="0px" style="font-size: small;">
<tr>
	<td>日期</td>
	<td>上证指数</td>
	<td>上证日涨跌幅(%)</td>
	<td>上证周涨跌幅(%)</td>
	<td>上证累计涨跌幅(%)</td>
	<td>仓位(%)</td>
	<td>成本金额</td>
	<td>市值金额</td>
	<td>日浮动盈亏</td>
	<td>日盈亏(%)</td>
	<td>周浮动盈亏</td>
	<td>周盈亏(%)</td>
	<td>月浮动盈亏</td>
	<td>月盈亏(%)</td>
	<td>年浮动盈亏</td>
	<td>年盈亏(%)</td>
	<td>总浮动盈亏</td>
	<td>总盈亏(%)</td>
</tr>
	<%
		List<Report> reports = (List<Report>)request.getAttribute("reports");
		DecimalFormat decimalFormat = new DecimalFormat("####");
		DecimalFormat decimalFormat1 = new DecimalFormat("0.00");
	%>
	
		<%
		Report nextReport = null;
		for(int i=reports.size()-1; i>=0 ; i--){
			Report report = reports.get(i);
			if(i != 0){
				nextReport  = reports.get(i-1); 
			}
			String isMdSty =  "";
			if(report.isMd()){
				isMdSty =  "bgcolor=\"#999999\"";
			}
			%>
			<tr style=" border:solid 1px #f00;">			
			<td <%=isMdSty %>><%=report.getCreateDate() %></td>
			<%
				if(report.getSzzsbili() > 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getSzzs()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getSzzsbili()) %>↑</td>
					<%
				}else if(report.getSzzsbili() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getSzzs()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getSzzsbili()) %>↓</td>
					<%
				}else{
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getSzzs()) %></td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			<%
				if(report.getSzzsbili_w() > 0){
					%>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getSzzsbili_w()) %></td>
					<%
				}else if(report.getSzzsbili_w() < 0){
					%>
					<td bgcolor="green"><%=decimalFormat1.format(report.getSzzsbili_w()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			<%
				if(report.getSzzsbili_t() > 0){
					%>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getSzzsbili_t()) %>↑</td>
					<%
				}else if(report.getSzzsbili_t() < 0){
					%>
					<td bgcolor="green"><%=decimalFormat1.format(report.getSzzsbili_t()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			<%
				if(nextReport==null || Math.rint(report.getCangwei()) == Math.rint(nextReport.getCangwei())){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getCangwei())%></td>
					<%
				}else if(Math.rint(report.getCangwei())>Math.rint(nextReport.getCangwei())){
					%>
					<td bgcolor="red"><%=decimalFormat.format(report.getCangwei())%>↑</td>
					<%
				}else{
					%>
					<td bgcolor="green"><%=decimalFormat.format(report.getCangwei())%>↓</td>
					<%
				}
			%>
			
			
			<td <%=isMdSty %>><%=decimalFormat.format(report.getBenjin()) %></td>
			<%
				if(report.getChenben() > 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getChenben()) %></td>
					<%
				}else if(report.getFudongkuiyin_t() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getChenben()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			
			
			<%
				if(report.getFudongkuiyin_d() > 0){
					%>
					<td <%=isMdSty %>>&nbsp;<%=decimalFormat.format(report.getFudongkuiyin_d()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getFudongkuiyinbili_d()) %>↑</td>
					<%
				}else if(report.getFudongkuiyin_d() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getFudongkuiyin_d()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getFudongkuiyinbili_d()) %>↓</td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			<%
				if(report.getFudongkuiyin_w() > 0){
					%>
					<td <%=isMdSty %>>&nbsp;<%=decimalFormat.format(report.getFudongkuiyin_w()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getFudongkuiyinbili_w()) %></td>
					<%
				}else if(report.getFudongkuiyin_w() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getFudongkuiyin_w()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getFudongkuiyinbili_w()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			<%
				if(report.getFudongkuiyin_m() > 0){
					%>
					<td <%=isMdSty %>>&nbsp;<%=decimalFormat.format(report.getFudongkuiyin_m()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getFudongkuiyinbili_m()) %></td>
					<%
				}else if(report.getFudongkuiyin_m() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getFudongkuiyin_m()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getFudongkuiyinbili_m()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			
			<%
				if(report.getFudongkuiyin_y() > 0){
					%>
					<td <%=isMdSty %>>&nbsp;<%=decimalFormat.format(report.getFudongkuiyin_y()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getFudongkuiyinbili_y()) %></td>
					<%
				}else if(report.getFudongkuiyin_y() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getFudongkuiyin_y()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getFudongkuiyinbili_y()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			
			<%
				if(report.getFudongkuiyin_t() > 0){
					%>
					<td <%=isMdSty %>>&nbsp;<%=decimalFormat.format(report.getFudongkuiyin_t()) %></td>
					<td bgcolor="red">&nbsp;<%=decimalFormat1.format(report.getFudongkuiyinbili_t()) %></td>
					<%
				}else if(report.getFudongkuiyin_t() < 0){
					%>
					<td <%=isMdSty %>><%=decimalFormat.format(report.getFudongkuiyin_t()) %></td>
					<td bgcolor="green"><%=decimalFormat1.format(report.getFudongkuiyinbili_t()) %></td>
					<%
				}else{
					%>
					<td <%=isMdSty %>>0.0</td>
					<td <%=isMdSty %>>0.0</td>
					<%
				}
			%>
			<%
		}
		%>
		
	


</table>
</body>
</html>