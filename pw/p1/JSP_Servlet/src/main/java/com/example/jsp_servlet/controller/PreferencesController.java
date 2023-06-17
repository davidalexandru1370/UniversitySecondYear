package com.example.jsp_servlet.controller;

import com.example.jsp_servlet.domain.DBManager;
import com.example.jsp_servlet.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "PreferencesController", value = "/preferences")
public class PreferencesController extends HttpServlet{
    public PreferencesController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        for (Cookie c : request.getCookies())
            if (c.getName().equals("user")) {
                int nr_of_questions_per_page = Integer.parseInt(request.getParameter("nr_of_questions_per_page"));
                int nr_of_questions_per_test = Integer.parseInt(request.getParameter("nr_of_questions_per_test"));
                response.addCookie(new Cookie("nr_of_questions_per_page", Integer.toString(nr_of_questions_per_page)));
                response.addCookie(new Cookie("nr_of_questions_per_test", Integer.toString(nr_of_questions_per_test)));
                response.sendRedirect("pages");
            }
        response.getWriter().println("Invalid request");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("preferences.jsp").include(request, response);
    }
}
