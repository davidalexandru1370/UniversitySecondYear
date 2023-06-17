package com.example.jsp_servlet.controller;
import com.example.jsp_servlet.domain.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "QuizController", value = "/pages")
public class QuizController extends HttpServlet{
    public QuizController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        DBManager db = new DBManager();
        int score=0;
        int total=0;
        String[] questionIds = request.getParameterValues("questionId");
        for(String questionId: questionIds)
        {
            if(db.IsAnswerCorrect(Integer.parseInt(request.getParameter("answers_"+questionId))))
                score++;
            total++;
        }
        request.getSession().setAttribute("score",score);
        request.getSession().setAttribute("noscore",total-score);
        request.getRequestDispatcher("result.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        boolean valid = false;
        int nr_of_questions_per_page=1;
        int nr_of_questions_per_test=2;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("nr_of_questions_per_page")) {
                nr_of_questions_per_page = Integer.parseInt(c.getValue());
                valid = true;
            }
            if (c.getName().equals("nr_of_questions_per_test")) {
                nr_of_questions_per_test = Integer.parseInt(c.getValue());
                valid = true;
            }
        }
        if(!valid) {
            response.getWriter().println("Invalid request");
        }
        else
        {
            request.getSession().setAttribute("nr_of_questions_per_page", Integer.toString(nr_of_questions_per_page));
            request.getSession().setAttribute("nr_of_questions_per_test", Integer.toString(nr_of_questions_per_test));
            request.getRequestDispatcher("pages.jsp").include(request, response);
        }
    }
}
