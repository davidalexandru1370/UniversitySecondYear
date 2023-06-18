<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
    <span id="score"></span>

<script>
    const value = sessionStorage.getItem("score");
    $(document).ready(function(){
        $("#score").text("You obtained " + value + " points");
    })
</script>
</body>
</html>
