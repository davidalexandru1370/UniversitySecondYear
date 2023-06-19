package webubb.controller;

import org.json.simple.JSONArray;
import webubb.domain.Entity;
import webubb.model.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SkeletonController extends HttpServlet {
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

        get(action, request, response);
        filter(action, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter(ACTION);

        add(action, request, response);
        delete(action, request, response);
        update(action, request, response);
    }

    private void get(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(GET.equals(action)) {
            ArrayList<Entity> entities = dbmanager.getEntities();

            JSONArray jsonAssets = new JSONArray();
            for (Entity e : entities) {
                jsonAssets.add(e.convertToJSONObject());
            }

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        }
    }

    private void filter(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(FILTER.equals(action)) {
            String name = request.getParameter("name");

            ArrayList<Entity> entities = dbmanager.filter(name);

            JSONArray jsonAssets = new JSONArray();
            for (Entity e : entities) {
                jsonAssets.add(e.convertToJSONObject());
            }

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonAssets.toJSONString());
            out.flush();
        }
    }

    private void add(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(ADD.equals(action)) {
            String name = request.getParameter("name");
            String date = request.getParameter("date");

            boolean result = dbmanager.add(name, date);

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(result);
            out.flush();
        }
    }

    private void delete(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(DELETE.equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            boolean result = dbmanager.delete(id);

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(result);
            out.flush();
        }
    }

    private void update(String action, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(UPDATE.equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String date = request.getParameter("date");

            boolean result = dbmanager.update(id, name, date);

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(result);
            out.flush();
        }
    }
}
