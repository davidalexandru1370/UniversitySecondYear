<%@ page import="webubb.domain.User" %>
<%@ page import="webubb.controller.LoginController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="webubb.domain.Project" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="display: flex; gap: 20px">
        <a href="succes.jsp">All projects</a>
        <a href="myProjects.jsp">My projects</a>
    </div>
    <%
        User user = (User) session.getAttribute("user");
        if(user != null){
            ArrayList<Project> projects = (ArrayList<Project>) ((ArrayList<Project>) session.getAttribute("projects"))
                    .stream()
                    .filter(p -> p.getProjectManagerId() == user.getId()).collect(Collectors.toList());
            for(Project project : projects){
                out.println("<p>" +project.getName()+ "</p>");
            }
        }
    %>
</body>
</html>
