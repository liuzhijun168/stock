<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.openflashchart.Graph"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.text.DecimalFormat"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	Graph g = new Graph();
	List<String> data = new ArrayList<String>();
	List<String> labels = new ArrayList<String>();
	List<String> links = new ArrayList<String>();
	List<String> colors = new ArrayList<String>();
	
	int max = 100;
	for(int i = 0; i < 3; i++) {
		double tmp = Math.random() * max;
		DecimalFormat format = new DecimalFormat("#.00");
		data.add(format.format(tmp));
		labels.add("商品" + (i+1));
		links.add("chart3.jsp?id=" + i);
	}
	

	g.title("统计图-来自JSP", "{font-size: 26px;}");
	g.pie(60, "#505050", "{font-size: 12px; color: #404040;}");
	g.pie_values(data, labels, links);
	colors.add("#d01f3c");
	colors.add("#356aa0");
	colors.add("#C79810");
	
	g.pie_slice_colours(colors);
	g.set_tool_tip("#val# ,元<br>");
%>
<%=g.render()%>
</body>
</html>