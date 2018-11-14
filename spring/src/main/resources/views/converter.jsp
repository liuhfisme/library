<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>
    <div id="resp"></div>
    <input type="button" onclick="req();" value="请求" />
<script type="text/javascript" src="assets/js/jquery-3.1.1.min.js/"></script>
<script type="text/javascript">
    function  req() {
        $.ajax({
            url: "convert",
            type: "post",
            data: "1-feifei.liu",
            contentType: "application/x-wisely",
            success: function (data) {
                $("#resp").html(data);
            }
        });
    }
</script>
</body>
</html>