<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传示例</title>
<jsp:include page="../include/common.jsp"></jsp:include>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="fileserver/css" />
<script type="text/javascript" src="fileserver/javascript"></script>
<script type="text/javascript">
    var gUploader;
    $(document).ready(function() {
        gUploader = new cn.mapway.ui.Uploader();
        gUploader.hide();
        $(document.body).append(gUploader.get());

    });
    function upload_confirm(index) {
        var vs = gUploader.getValue();
        console.log($.toJSON(vs));
        layer.close(index);
    }
    function cancel_confirm() {

    }
    function upload() {

        gUploader.clear();
        layer.open({
            type : 1,
            title : "文件上传",
            area : [ '500px' ],
            content : gUploader.get(),
            btn : [ '确定', '取消' ],
            yes : upload_confirm,
            btn1 : cancel_confirm
        });

    }
</script>


</head>
<body>
	<jsp:include page="../include/top.jsp"></jsp:include>

	<div class="ui container">
		<button onclick="upload()">上传文件</button>
	</div>

</body>
</html>