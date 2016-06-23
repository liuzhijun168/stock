<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Flash Chart Demo 2</title>
</head>
<body>
<%
String url = request.getProtocol().substring(0, 4).toLowerCase() + "://" + request.getRemoteHost() + ":" + request.getLocalPort() + request.getContextPath();
int width = 500;
int height = 250;
out.print("得到传递参数为：" + request.getParameter("id"));
%>
<br/>
<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
	codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
	width="<%=width%>" height="<%=height%>" id="ie_chart"
	align="middle">
	<param name="allowScriptAccess" value="sameDomain" />
	<param name="movie"
		value="<%=url%>/charts/open-flash-chart.swf?width=<%=width%>&height=<%=height%>&data=<%=url%>/chart-data-bar.jsp" />
	<param name="quality" value="high" />
	<param name="bgcolor" value="#FFFFFF" />
	<embed src="<%=url%>/open-flash-chart.swf?data=<%=url%>/chart-data-bar.jsp" quality="high"
		bgcolor="#FFFFFF" width="<%=width%>" height="<%=height%>"
		name="chart" align="middle" allowScriptAccess="sameDomain"
		type="application/x-shockwave-flash"
		pluginspage="http://www.macromedia.com/go/getflashplayer"
		id="chart" />
</object>
	&nbsp;&nbsp;&nbsp;&nbsp;
<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
	codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0"
	width="<%=width%>" height="<%=height%>" id="ie_chart"
	align="middle">
	<param name="allowScriptAccess" value="sameDomain" />
	<param name="movie"
		value="<%=url%>/charts/open-flash-chart.swf?width=<%=width%>&height=<%=height%>&data=<%=url%>/chart-data-bar3D.jsp" />
	<param name="quality" value="high" />
	<param name="bgcolor" value="#FFFFFF" />
	<embed src="<%=url%>/open-flash-chart.swf?data=<%=url%>/chart-data-bar3D.jsp" quality="high"
		bgcolor="#FFFFFF" width="<%=width%>" height="<%=height%>"
		name="chart" align="middle" allowScriptAccess="sameDomain"
		type="application/x-shockwave-flash"
		pluginspage="http://www.macromedia.com/go/getflashplayer"
		id="chart" />
</object>
</body>
</html>