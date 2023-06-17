<%@ page import="webubb.domain.Project" %>
<%@ page import="webubb.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        .asset-name {
            background-color: cornflowerblue;
            border-right: solid 1px black;
        }
    </style>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
<%! User user; %>
<%  user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("Welcome "+user.getUsername());
%>
        <br/>
        <div style="display: flex; gap: 20px">
            <a href="succes.jsp">All projects</a>
            <a href="myProjects.jsp">My projects</a>
        </div>
        <div>
            <table id="all-projects">
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>project manager id</td>
                        <td>name</td>
                        <td>description</td>
                        <td>members</td>
                    </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
<%
    }
%>

<script>
    $(document).ready(function(){
        getAllProjects(function(projects) {
            for(let project of projects){
                console.log(project);
                var tableRef = document.getElementById('all-projects').getElementsByTagName('tbody')[0];
                var newRow = tableRef.insertRow(tableRef.rows.length)
                const innerHtml = "<tr style=border: 1px solid black" +
                    "<td>" + project.id +"</td>" +
                    "<td>" +project.projectManagerId+"</td>" +
                    "<td>"+ project.name + "</td>" +
                    "<td>"+ project.description + "</td>" +
                    "<td>"+ project.members + "</td>" +
                    "</tr>"
                newRow.innerHTML = innerHtml;
            }
        })
    })
</script>

</body>
</html>