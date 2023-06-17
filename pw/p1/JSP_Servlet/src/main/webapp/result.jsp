<%@ page import="com.example.jsp_servlet.domain.DBManager" %><%--
  Created by IntelliJ IDEA.
  User: G
  Date: 5/15/2023
  Time: 4:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
</head>
<body>
<div><p>Correct answers: <%
    Integer score = (Integer) session.getAttribute("score");
    if (score != null)
        out.println(score);
%></p></div>
<div><p>Wrong answers: <%
    Integer noscore = (Integer) session.getAttribute("noscore");
    if (noscore != null)
        out.println(noscore);
%></p></div>
<div><p>Personal best: <%
    int the_best;
    DBManager db = new DBManager();
    for (Cookie c : request.getCookies()) {
        if (c.getName().equals("user")) {
            the_best = db.getUserFromName(c.getValue()).getAll_time_best();
            if(score > the_best) {
                the_best = score;
                db.setNewBest(c.getValue(), score);
            }
            out.println(the_best);
        }
    }
%></p></div>
<div><button onclick="logout()">Logout</button></div>
</body>
</html>
<script>
    function logout() {
        document.cookie = 'user=; expires=Thu, 01 Jan 1970 00:00:00 UTC;'
        $(location).attr("href", "logout");
    }
</script>