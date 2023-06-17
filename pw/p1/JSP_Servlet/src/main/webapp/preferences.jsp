<%--
  Created by IntelliJ IDEA.
  User: G
  Date: 5/13/2023
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Preferences Page</title>
    <link rel="stylesheet" href="login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="preferences.js"></script>
</head>
<body>
<div class="wrapper">
    <h1> Preferences </h1>
    <h5> Give us your preferences before you answer the questions </h5>
    <%--suppress HtmlUnknownTarget --%>
    <form action="preferences" method="post">
        <label>
            <input type="number" name="nr_of_questions_per_page" placeholder="Nr of questions per page" autocomplete="off">
        </label>
        <label>
            <input type="number" name="nr_of_questions_per_test" placeholder="Nr of questions per test" autocomplete="off">
        </label>
        <%
            String err = (String) session.getAttribute("error");
            if (err != null)
                out.println("<p>" + err + "</p>");
        %>
        <div id="buttons">
            <button type="submit">Go to quiz</button>
        </div>
    </form>
</div>
</body>
</html>
