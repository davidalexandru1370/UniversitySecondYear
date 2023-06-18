<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
    <button id="next">next</button>
    <span id="question-text"></span>
    <input id="answer">
<script>
    let index = 0;
    const questions = JSON.parse(sessionStorage.getItem("questions"));
    const answers = [];
    $(document).ready(function(){
        $("#question-text").text(questions[0].text);
    })

    $("#next").click(function(){
        if(index >= questions.length - 1){
            const answer = $("#answer").val();
            answers.push({questionId: questions[index].id, answer: answer})
            $.post("QuestionController",JSON.stringify(answers),function(data){
                console.log(data);
                sessionStorage.setItem("result",data.score);
                alert("You obtained " + data.score + " points");
                //window.location.replace("score.jsp");

            });
        }
        else{
            const answer = $("#answer").val();
            answers.push({questionId: questions[index].id, answer: answer})
            $("#answer").val("");
            index += 1;
            $("#question-text").text(questions[index].text);
        }
    })
</script>
</body>
</html>
