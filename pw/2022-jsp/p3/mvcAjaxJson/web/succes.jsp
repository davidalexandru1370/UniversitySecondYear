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
<%! String user; %>
<% user = session.getAttribute("user").toString();
    if (user != null) {
        out.println("Welcome " + user);


%>
<br/>
<div>
    <table>
        <th>
            <tr>
        <td>id</td>
        <td>text</td>
        <td>correct answer</td>
        <td>wrong answer</td>
        </tr>
        </th>
        <tbody id="table-body"></tbody>
    </table>
    <div style="display: flex">
        <span>Number of questions:</span>
        <input id="number-of-questions">
        <button id="get-inputs">Prepare quiz</button>
    </div>
    <div id="content">

    </div>
</div>
<%
    }
%>
<script>

    $(document).ready(function () {
        getAllQuestions(function (data) {
            Helper.allQuestions = data;
            for (const question of data) {
                const innerHtml = "<tr style=\"border: 1px solid black\">" +
                    "<td>" + question.id + "</td>" +
                    "<td>" + question.text + "</td>" +
                    "<td>" + question.correctAnswer + "</td>" +
                    "<td>" + question.wrongAnswer + "</td>" +
                    +"</tr>"
                $("#table-body").append(innerHtml);
            }


        })
    })

    $("#get-inputs").click(function(){
        Helper.number = 0;
        const parent = $("#content");
        const numberOfFields = $("#number-of-questions").val();
        parent.empty();     

        for(let i = 0 ; i < numberOfFields; i++){
            const innerHtml = "<div style='margin-bottom: 10px' >" +
                "Enter question: <input type=\"text\" name=\"question\" id=qid" + Helper.number + "> <BR>" +
                "</div>"
            parent.append(innerHtml);
            Helper.number += 1
        }

        const startQuizButton = "<button id=\"start-quiz\">start quiz</button>";
        parent.append(startQuizButton);

    })

    $(document).on("click","#start-quiz",function(){
        const questions = []
        for(let i = 0; i < Helper.number; i++){
            const questionId =  parseInt($("#qid" + i).val());
            const question = Helper.allQuestions.filter(q => q.id === questionId);
            questions.push(question[0]);
        }

        sessionStorage.setItem("questions",JSON.stringify(questions));
        window.location.replace("play.jsp");
    })
</script>

</body>
</html>