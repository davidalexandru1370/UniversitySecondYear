package webubb.controller;

/**
 * Created by forest.
 */


import java.io.IOException;



import webubb.model.DBManager;
import webubb.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        DBManager dbmanager = new DBManager();
        boolean canAuthenticate = dbmanager.authenticate(username, password);
        if (canAuthenticate) {
            rd = request.getRequestDispatcher("/success.jsp");

            // Session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Cookie
            Cookie usernameCookie = new Cookie("username", username);
            response.addCookie( usernameCookie );
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }

}