<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A Flash Chart</title>
</head>
<body>
<%
String url = request.getProtocol().substring(0, 4).toLowerCase() + "://" + request.getRemoteHost() + ":" + request.getLocalPort() + request.getContextPath();

String width = "500";
String height = "350";
%>
<br/>
<center>
	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
		codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
		width="<%=width%>" height="<%=height%>" id="ie_chart"
		align="middle">
		<param name="allowScriptAccess" value="always" />
		<param name="movie"
			value="<%=url%>/charts/open-flash-chart.swf?width=500&height=250&data=<%=url%>/charts/chart-data-line.jsp" />
		<param name="quality" value="high" />
		<param name="bgcolor" value="#FFFFFF" />
		<embed src="<%=url%>/charts/open-flash-chart.swf?data=<%=url%>/charts/chart-data-line.jsp" quality="high"
			bgcolor="#FFFFFF" width="<%=width%>" height="<%=height%>"
			name="chart" align="middle" allowScriptAccess="always"
			type="application/x-shockwave-flash"
			pluginspage="http://www.macromedia.com/go/getflashplayer"
			id="chart" />
	</object>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
		codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
		width="<%=width%>" height="<%=height%>" id="ie_chart"
		align="middle">
		<param name="allowScriptAccess" value="always" />
		<param name="movie"
			value="<%=url%>/charts/open-flash-chart.swf?width=500&height=250&data=<%=url%>/charts/chart-data-pie.jsp" />
		<param name="quality" value="high" />
		<param name="bgcolor" value="#FFFFFF" />
		<embed src="<%=url%>/charts/open-flash-chart.swf?data=<%=url%>/charts/chart-data-pie.jsp" quality="high"
			bgcolor="#FFFFFF" width="<%=width%>" height="<%=height%>"
			name="chart" align="middle" allowScriptAccess="always"
			type="application/x-shockwave-flash"
			pluginspage="http://www.macromedia.com/go/getflashplayer"
			id="chart" />
	</object>
</center>
</body>
</html>