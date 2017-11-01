<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../include/common.jsp"></jsp:include>
<title>资源权限管理</title>
<style type="text/css">
#main_left {
	margin-top: 80px;
}
</style>
</head>
<body>
	<jsp:include page="../../include/top.jsp"></jsp:include>
	<div class="ui container">
		<div class="ui four column grid">
			<div class="column">
			 		<div class="ui attached top second header">
			 			角色
			 		</div>
			 		<div class="ui attached bottom list">
			 			<c:forEach items="${roles }" var="role">
						<div class="item">
							<div class="content">
								<a class="header" roleId="${role.id }">${role.name }</a>
								<div class="description">${role.summary }</div>
							</div>
						</div>
					</c:forEach>
			 		</div>
			</div>	
		</div>
	</div>
</body>
</html>