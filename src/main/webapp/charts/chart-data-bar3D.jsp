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
	b.setVar("bar_3d");
	//b.setVar("bar_glass");
	//b.setVar("bar_fade");
	Bar b2 = new Bar("50", "#0000FF");
	b2.setVar("bar_3d");
	//b2.setVar("bar_glass");
	//b2.setVar("bar_fade");
	b.key("page views", 15);
	b2.key("Bounces", 15);
	int max = 100;
	for(int i = 0; i < 12; i++) {
		b.add(String.valueOf(Math.random() * max), String.valueOf(i));
		b2.add(String.valueOf(Math.random() * max), String.valueOf(i));
	}
	
	// Spoon sales, March 2007
	g.title("统计图-来自JSP", "{font-size: 26px;}");
	g.getData_sets().add(b);
	g.getData_sets().add(b2);
	g.bar_3D("75", "#D54C78", "2006", 8);
	g.bar_3D("75", "#3334AD", "2007", 8);
	
	//g.bar_glass("75", "#D54C78", "");
	//g.bar_glass("75", "#D54C78", "");
	
	//g.bar_fade("75");
	//g.bar_fade("75");
	
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
	g.set_x_label_style("10", "#9933CC", 2, 2, "");
	g.set_x_axis_3d(5);
	
	g.x_axis_colour("#909090", "#ADB5C7");
	g.x_axis_colour("#909090", "#ADB5C7");

	g.set_y_max(120);
	g.y_label_steps(10);
%>
<%=g.render()%>
</body>
</html>