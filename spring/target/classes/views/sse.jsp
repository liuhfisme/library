<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>
    <div id="msgFrompPush"></div>
<script type="text/javascript" src="assets/js/jquery-3.1.1.min.js/"></script>
<script type="text/javascript">
    if (!!window.EventSource) {
        var source = new EventSource("push");
        s= "";
        source.addEventListener("message", function (e) {
            s+=e.data+"<br/>";
            $("#msgFrompPush").html(s);
        });

        source.addEventListener("open", function (e) {
            console.log("链接打开。");
        }, false);

        source.addEventListener("error", function (e) {
           if(e.readyState == EventSource.CLOSED) {
               console.log("链接关闭。");
           } else {
               console.log(e.readyState);
           }
        }, false);
    } else {
        console.log("你的浏览器不支持SSE。");
    }
</script>
</body>
</html>