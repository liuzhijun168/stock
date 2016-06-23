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
	
	for(double i=0; i<6.2; i+=0.2)
	{
	  double tmp = java.lang.Math.sin(i) * 1.9;	
	  data.add(String.valueOf(tmp));
	  DecimalFormat format = new DecimalFormat("#.00");
	  labels.add(format.format(tmp));
	  //labels.add(String.valueOf(tmp));
	}

	g.title("统计图,来自JSP", "{font-size: 26px;}");
	g.set_data(data);
	g.area_hollow("2", "3", "25", "#CC3399", "text", "12", "#006699");

	g.set_x_labels(labels);
	g.set_x_label_style("10", "#9933CC", 2, 2, "");
	g.set_x_axis_steps(2);

	g.set_y_format("#val# (万)");
	g.set_is_decimal_separator_comma(true);
	g.set_is_fixed_num_decimals_forced(true);
	g.set_is_thousand_separator_disabled(true);
	g.set_num_decimals(2);
	
	g.set_y_min(-2);
	g.set_y_max(2);

	g.y_label_steps(4);
	g.set_y_legend("Open Flash Chart", 12, "#736AFF");

%>
<%=g.render()%>
</body>
</html>