package webubb.controller;

import webubb.model.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyController extends HttpServlet {
    private final String ACTION = "action";
    private final String GET = "get";
    private final String ADD = "add";
    private final String DELETE = "delete";
    private final String UPDATE = "update";
    private final String FILTER = "filter";


    private final DBManager dbmanager = new DBManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);

//        get(action, request, response);
//        filter(action, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);

//        add(action, request, response);
//        delete(action, request, response);
//        update(action, request, response);
    }

    private void get(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(GET.equals(action)) {
//
//        }
    }

    private void filter(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(FILTER.equals(action)) {
//
//        }
    }

    private void add(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(ADD.equals(action)) {
//
//        }
    }

    private void delete(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(DELETE.equals(action)) {
//
//        }
    }

    private void update(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(UPDATE.equals(action)) {
//
//        }
    }
}
