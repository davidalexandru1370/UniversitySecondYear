<%@ page import="webubb.domain.Topic" %>
<%@ page import="webubb.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: david
  Date: 23.05.2023
  Time: 01:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Topic</title>
    <script src="js/jquery-2.0.3.js"></script>

</head>
<body>
    <div style="display: flex; flex-direction: column; justify-content: space-between; height: 90vh">
        <div>
            <p id="topic-title"></p>
            <span id="topic-content" style="font-size: 10px"></span>
            <div id="comments">
                <p>Comments:</p>
            </div>
        </div>
        <div style="display: flex">
            Enter comment: <input type="text" name="comment" id="input-comment"><br>
            <button type="submit" id="add-comment">Add</button>
        </div>
    </div>
</body>
</html>

<script>
    const topicId = location.search.split('=')[1]
    $(document).ready(function(){
        getTopicById(topicId,function(topic) {
            console.log(topic)
            $("#topic-title").text(topic.title)
            $("#topic-content").text(topic.content)
        })

        getCommentsOfTopic(topicId,function(comments){
            console.log(comments);
            const  ownername = "<%= ((User) session.getAttribute("user")).getUsername() %>";
            for(var comm in comments){
                $("#comments").append("<span style=\"font-size: 10px\"> " + comments[comm].ownername+ "</span><br>" +
                    "<span>" +comments[comm].text + "</span><br><br><br>");

                if(comments[comm].ownername.localeCompare(ownername) === 0){
                    $("#comments").append("<button onclick=\"deleteCommentById(" + comments[comm].id+")\">Delete</button>");
                }


            }
        })
    })

    $("#add-comment").click(function() {
       const  ownername = "<%= ((User) session.getAttribute("user")).getUsername() %>";
       const text = $("#input-comment").val();

       $.post("CommentController?text=" + text +"&ownername=" + ownername + "&topicId=" + topicId + "", function(){
            location.reload();
       })

    })
    function getTopicById(id, callbackFunction){
        return $.getJSON("TopicController",{
            action:"one", id: id
        }, callbackFunction);
    }
    function getCommentsOfTopic(topicId,callbackFunction){
        return $.getJSON("CommentController",{
            id:topicId
        }, callbackFunction);
    }

    function deleteCommentById(commentId){
        $.ajax({
            url:"CommentController?id=" + commentId +"",
            type:"DELETE",
            success: function(){
                location.reload()
            }
        })
    }

</script>