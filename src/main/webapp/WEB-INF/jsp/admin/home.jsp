<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/common.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>

	<div class="pusher">
		<div class="ui container">
			<div class="ui large secondary  pointing menu">
				<a class="toc item"> <i class="sidebar icon"></i>
				</a> <a class="active item" href="/index">首页</a> <a class="item">项目</a>
				<a class="item">关于</a><a class="item" href="doc/index">API</a> <a
					class="item" href="admin/auth/index">权限</a> <a class="item"
					href="files/upload">文件上传</a>
				<div class="right item">
					<a class="ui inverted button" href="/logout">${user.name} </a> <a
						class="ui inverted button" href="/reg">注册</a>
				</div>
			</div>
		</div>

	</div>

</body>
</html>