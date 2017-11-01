<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线交付</title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

#myiframe {
	overflow: hidden;
}
</style>
<script type="text/javascript">
    function changeFrameHeight() {
        var ifm = document.getElementById("myiframe");
        ifm.height = document.documentElement.clientHeight-4;
    }
    window.onresize = function() {
        changeFrameHeight();
    }
</script>
</head>
<body onload="changeFrameHeight()">
	<iframe src="http://ubi3.nengliwa.com/"  width="100%" id="myiframe"
		onload="changeFrameHeight()" frameborder="0px"> </iframe>
</body>
</html>