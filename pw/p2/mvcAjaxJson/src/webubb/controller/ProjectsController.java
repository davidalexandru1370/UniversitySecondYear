package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Project;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProjectsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DBManager dbManager = new DBManager();

        if((action != null) && action.equals("getAll")){
            ArrayList<Project> projects = dbManager.getAllProjects();
            response.setContentType("application/json");
            JSONArray jsonProjects = new JSONArray();
            for(Project project : projects){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", project.getId());
                jsonObject.put("projectManagerId", project.getProjectManagerId());
                jsonObject.put("name", project.getName());
                jsonObject.put("description", project.getDescription());
                jsonObject.put("members", project.getMembers());
                jsonProjects.add(jsonObject);
            }
            request.getSession().setAttribute("projects", projects);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonProjects.toJSONString());
            out.flush();
        }
    }
}
