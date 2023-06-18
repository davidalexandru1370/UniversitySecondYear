<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
    <button id="next-button">Next</button>
    <button id="previous-button">Previous</button>
    <div id="all-content">

    </div>

<script>
    let contents = [];
    $(document).ready(function(){
         $.getJSON("ContentController",{},function(data){
             contents = data;
                 const innerHtml = "<p>" + data[0].date + " " + data[0].title + " " +  data[0].description+ " " + data[0].url+  "</p>"
                 $("#all-content").append(innerHtml)

        })
        setInterval(function(){
            $.getJSON("ContentController",{},function(data){
                if(data[0].id !== contents[0].id){
                    alert("New content was added");
                    contents = data;
                }
            })
        },3000)
    })


    $("#next-button").click(function(){
        Helper.number = (Helper.number + 1) % 4
        $("#all-content").empty();
        const innerHtml = "<p>" + contents[Helper.number].date + contents[Helper.number].title + contents[Helper.number].description + contents[Helper.number].url+  "</p>"
        $("#all-content").append(innerHtml)
    })

    $("#previous-button").click(function(){
        Helper.number -= 1
        if(Helper.number < 0){
            Helper.number = 3
        }
        $("#all-content").empty();
        const innerHtml = "<p>" + contents[Helper.number].date + contents[Helper.number].title + contents[Helper.number].description + contents[Helper.number].url+  "</p>"
        $("#all-content").append(innerHtml)
    })
</script>
</body>
</html>
