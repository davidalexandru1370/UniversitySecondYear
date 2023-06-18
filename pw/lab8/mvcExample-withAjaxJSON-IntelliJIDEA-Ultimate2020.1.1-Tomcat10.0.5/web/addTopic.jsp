<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 23.05.2023
  Time: 00:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new topic</title>
</head>
<body>
<form action="TopicController" method="post">
Enter title : <input type="text" name="title"> <BR>
Enter content : <input type="text" name="content"> <BR>
<input type="submit" value="Add New Topic"/>
</form>
</body>
</html>
