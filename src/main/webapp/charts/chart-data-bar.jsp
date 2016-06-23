<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.openflashchart.Graph"%>
<%@ page import="org.openflashchart.Bar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%

	Graph g = new Graph();
	Bar b = new Bar("50", "#9933CC");
	Bar b2 = new Bar("50", "#0000FF");
	b.key("page views", 15);
	b2.key("Bounces", 15);
	//List<String> links = new ArrayList<String>();
	int max = 100;
	for(int i = 0; i < 12; i++) {
		b.add(String.valueOf(Math.random() * max), "javascript:alert('hello,world!')");
		b2.add(String.valueOf(Math.random() * max), "http://www.google.com");
	}
	
	// Spoon sales, March 2007
	g.title("统计图-来自JSP", "{font-size: 26px;}");
	g.getData_sets().add(b);
	g.getData_sets().add(b2);
	g.bar("0");
	g.bar("0");
	
	// label each point with its value
	List<String> labels = new ArrayList<String>();
	labels.add("一月");
	labels.add("二月");
	labels.add("三月");
	labels.add("四月");
	labels.add("五月");
	labels.add("六月");
	labels.add("七月");
	labels.add("八月");
	labels.add("九月");
	labels.add("十月");
	labels.add("十一月");
	labels.add("十二月");
	g.set_x_labels(labels);
	g.set_x_label_style("12", "#006699", 0, 2, "");
	g.set_x_axis_steps(2);
	g.set_x_tick_size(5);
	g.set_bg_colour("#eaf7fc");


	g.set_y_max(100);
	g.y_label_steps(10);
%>
<%=g.render()%>
</body>
</html>