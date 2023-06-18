<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
</head>
<body>
  <div style="display: flex; gap: 20px">
    <button id="add-content">add content</button>
    <button id="save-content">save content</button>
  </div>
  <div id="content">

  </div>

<script>
  $("#add-content").click(function(){
    const parent = $("#content");
    const innerHtml = "<div style='margin-bottom: 10px' >" +
            "Enter title: <input type=\"text\" name=\"username\" id=title" + Helper.number + "> <BR>" +
            "Enter description: <input type=\"text\" name=\"password\" id=description" +Helper.number+ "> <BR>" +
            "Enter url: <input type=\"text\" name=\"password\" id=url" + Helper.number +"> <BR>" +
            "</div>"
    parent.append(innerHtml);
    Helper.number += 1
  })

  $("#save-content").click(function(){
    Helper.content = []
    for(let i = 0; i < Helper.number; i++){
        const title = document.getElementById("title" + i).value;
        const description = document.getElementById("description" + i).value;
        const url = document.getElementById("url" + i).value;
        Helper.content.push({
          title: title,
          description: description,
          url: url
        })
    }
    console.log(Helper.content);

    $.post("ContentController", JSON.stringify(Helper.content),function(){});
    Helper.number = 0
  })
</script>

</body>
</html>
