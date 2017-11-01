<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/common.jsp"></jsp:include>
<script type="text/javascript" src="/doc/javascript"></script>
<style type="text/css">
.main.container {
	margin: 100px 0px;
}

.login.card {
	width: 500px;
}
</style>
<script type="text/javascript">
	var url = "${url}";
	function doLogin() {
		$("#msgPanel").hide();
		var req = {
			userName : $("#name").val(),
			pwd : $("#pwd").val(),
			accountType : $("#accountType").val(),
			code : "",
			clientId : "web"
		}
		Api.login(req, onLogin);
	}

	function onLogin(data) {
		if (data.retCode != 0) {
			$("#msgPanel").show();
			$("#msg").text(data.msg);
		} else {
			window.location.href = url;
		}
	}

	$(document).ready(function() {
		$('.ui.dropdown').dropdown();
	});
</script>
</head>
<body>
	<jsp:include page="include/top.jsp"></jsp:include>

	<div class="main ui container">
		<div class="login ui centered card">
			<div class="ui image">
				<img src="img/login-header.jpg">
			</div>

			<div class="ui content ">
				<div class="ui  hidden warning message" id="msgPanel">
					<i class="icon help"></i> <span id="msg"></span>
				</div>
				<div class="ui form">

					<div class="field">
						<label>账户类型</label> <select class="ui dropdown" name="accountType"
							id="accountType">
							<option value="0">注册账号</option>
							<option value="1">集团账号</option>
						</select>


					</div>
					<div class="ui field">
						<label>用户名</label>
						<div class="ui left icon input">
							<i class="user icon"></i> <input type="text" id="name"
								value="admin" placeholder="用户名" />
						</div>

					</div>
					<div class="ui field">
						<label>密码</label>
						<div class="ui left icon input">
							<i class="lock icon"></i> <input type="password" id="pwd"
								value="123456" placeholder="密码" />
						</div>
					</div>
				</div>
			</div>
			<div class="ui bottom attached button" onclick="doLogin()">
				<i class="sign in icon"></i> 登录
			</div>

		</div>
	</div>
</body>
</html>