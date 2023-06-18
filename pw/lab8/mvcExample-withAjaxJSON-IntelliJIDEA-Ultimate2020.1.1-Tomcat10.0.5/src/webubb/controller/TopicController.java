package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;

public class TopicController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if(title.isEmpty() || content.isEmpty()){
            request.getSession().setAttribute("error","Invalid post");
            request.getRequestDispatcher("main.jsp").include(request,response);
            return;
        }

        DBManager dbManager = new DBManager();
        dbManager.addTopic(title,content);
        request.getRequestDispatcher("main.jsp").include(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String action = request.getParameter("action");

        if((action != "null") && action.equals("all")) {
            DBManager dbManager = new DBManager();
            var result = dbManager.getAllTopics();
            request.getSession().setAttribute("topics", result);
            request.getRequestDispatcher("main.jsp").include(request, response);
        }
        else{
            response.setContentType("application/json");
            Integer id = Integer.parseInt(request.getParameter("id"));
            DBManager dbManager = new DBManager();
            var result = dbManager.getTopicById(id);
            JSONArray jsonAssets = new JSONArray();
            JSONObject jObj = new JSONObject();
            jObj.put("id", result.getId());
            jObj.put("content", result.getContent());
            jObj.put("title",result.getTitle());
            jsonAssets.add(jObj);

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jObj.toJSONString());
            out.flush();

        }
    }



}
