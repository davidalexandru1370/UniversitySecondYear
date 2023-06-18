<%@ page import="webubb.domain.User" %>
<%@ page import="webubb.domain.Topic" %>
<%@ page import="java.util.List" %>
<%@ page import="webubb.controller.TopicController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="display: flex; flex-direction: column">
<%! User user; %>
<%  user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("Welcome " + user.getUsername());
    }
%>
<a href="addTopic.jsp">Create new topic</a>
<ul>
    <%
        List<Topic> topics = (List<Topic>) request.getSession().getAttribute("topics");
        out.println("<div style=\"display:flex; flex-direction:column\">");
            for(Topic topic : topics){
                out.println("<a href=\"/topic.jsp?id=" + topic.getId() + "\">"  + topic.getTitle() + "</a>");
            }
        out.println("</div>");

    %>
</ul>
</body>
</html>


<script>
    function redirect(topicId){
        window.location.href = "localhost:8080/topic/" + topicId;
    }

</script>