<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="login.js"></script>
</head>
<body>
<div class="wrapper">
    <h1> Welcome to <em>THE QUIZ</em>! </h1>
    <h5> Please log in and then give us your preferences before you answer the questions </h5>
    <%--suppress HtmlUnknownTarget --%>
    <form action="login" method="post">
        <label>
            <input type="text" name="username" placeholder="Username:" autocomplete="off">
        </label>
        <label>
            <input type="password" name="password" placeholder="Password:" autocomplete="off">
        </label>
        <%
            String err = (String) session.getAttribute("error");
            if (err != null)
                out.println("<p>" + err + "</p>");
        %>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>