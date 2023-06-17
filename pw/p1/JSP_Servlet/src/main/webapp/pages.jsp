<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.jsp_servlet.domain.*" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quiz</title>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
  <style>
    .tab {
      display: none;
    }
  </style>
</head>
<body>
  <div><h1>Quiz</h1></div>
  <div><p>Total Questions: <%
    String all = (String) session.getAttribute("nr_of_questions_per_test");
    if (all != null)
      out.println(all);
  %></p></div>
  <br>
  <%
    DBManager db = new DBManager();
    List<Question> questions = db.getNQuestions(Integer.parseInt((String) session.getAttribute("nr_of_questions_per_test")));
    int show=Integer.parseInt((String) session.getAttribute("nr_of_questions_per_page"));
    int current = show;
    out.println("<form id=\"quizForm\" action=\"pages\" method=\"post\">");
    for(Question q: questions)
    {
      if(current == show) {
        out.println("<div class=\"tab\">");
        current = 0;
      }
      current++;
      out.println("<h3>"+q.getQuestion()+"</h3>");
      out.println("<label><input type=\"hidden\" name=\"questionId\" value=\""+q.getId()+"\"></label>");
      for(Answer a: db.AnswersOfQuestion(q.getQuestion())) {
        out.println("<label>");
        out.println("<input type=\"radio\" required=\"required\" name=\"answers_"+q.getId()+"\" value=\""+a.getId()+"\" oninput=\"this.className = ''\"/>"+a.getAnswer()+"</label>");
      }
      if(current == show) {
        out.println("</div>");
      }
    }
    if(current != show) {
      out.println("</div>");
    }
    %><div>
      <button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
      <button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
    </div>
  <%
    out.println("<div><button type=\"submit\">Submit</button></div>");
    out.println("</form>");
  %>
</body>
</html>
<script>
  var currentTab = 0; // Current tab is set to be the first tab (0)
  showTab(currentTab); // Display the current tab

  function showTab(n) {
    // This function will display the specified tab of the form ...
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    // ... and fix the Previous/Next buttons:
    if (n == 0) {
      document.getElementById("prevBtn").style.display = "none";
    } else {
      document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 1)) {
      document.getElementById("nextBtn").style.display = "none";
    } else {
      document.getElementById("nextBtn").style.display = "inline";
    }
  }

  function nextPrev(n) {
    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= x.length) {
      //...the form gets submitted:
      document.getElementById("quizForm").submit();
      return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
  }
</script>