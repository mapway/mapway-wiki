<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../include/common.jsp"></jsp:include>
<title>资源请求失败</title>
</head>
<body>
		<h1>请求错误</h1>
		<div>${data}</div>
		<div>资源:${url }</div>
</body>
</html>