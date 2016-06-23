<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.openflashchart.Graph"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	int max = 50;
	List<String> data = new ArrayList<String>();
	//List<String> data2 = new ArrayList<String>();
	//List<String> data3 = new ArrayList<String>();
	List<String> links = new ArrayList<String>();
	for(int i = 0; i < 12; i++) {
		data.add(Double.toString(Math.random() * max));
		//data2.add(Double.toString(Math.random() * max / 2));
		//data3.add(Double.toString(Math.random() * max / 3));
		links.add("javascript:alert('hello, i am no."+(i+1)+"')");
	}

	Graph g = new Graph();

	// Spoon sales, March 2007
	g.title("统计图-来自JSP", "{font-size: 25px;}");
	g.set_data(data);
	//g.set_data(data2);
	//g.set_data(data3);
	
	g.line(2, "0x9933CC", "Page views", 10, 2);
	//g.line_hollow("2", "4", "0x80a033", "Bounces", "10");
	//g.line_dot("5", "5", "0x006699");

	g.set_links(links);
	
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
	g.set_x_label_style("12", "#FF0000", 0, 2, "");
	g.set_x_legend("Open Flash Chart Demo", 12, "#736AFF");
	
	// set the Y max
	g.set_y_max(60);
	// label every 20 (0,20,40,60)
	g.y_label_steps(6);
%>
<%=g.render()%>
</body>
</html>