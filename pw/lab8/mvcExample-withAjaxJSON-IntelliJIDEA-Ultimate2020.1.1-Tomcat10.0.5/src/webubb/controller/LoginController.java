package webubb.controller;

/**
 * Created by forest.
 */


import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import webubb.model.DBManager;
import webubb.domain.User;



public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("")) {
            request.getSession().setAttribute("error", "Username must not be null!");
            request.getRequestDispatcher("index.html").include(request, response);
        }
        else if (password.isEmpty()) {
            request.getSession().setAttribute("error", "Password must not be null!");
            request.getRequestDispatcher("index.html").include(request, response);
        }
        else {
            DBManager dbManager = new DBManager();
            User user = dbManager.authenticate(username, password);
            if (user != null) {
                response.addCookie(new Cookie("user", user.getUsername()));
                var topics = dbManager.getAllTopics();
                request.getSession().setAttribute("topics",topics);
                request.getSession().setAttribute("user",user);
                response.sendRedirect("main.jsp");
            } else {
                request.getSession().setAttribute("error", "Username or password invalid!");
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }
    }

}