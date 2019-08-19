<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Document</title>
</head>
<body>

<script type="text/javascript" src="assets/js/jquery-3.1.1.min.js/"></script>
<script type="text/javascript">
    deferred();

    function deferred() {
        $.get("defer", function (data) {
            console.log(data);
            deferred();
        })
    }
</script>
</body>
</html>