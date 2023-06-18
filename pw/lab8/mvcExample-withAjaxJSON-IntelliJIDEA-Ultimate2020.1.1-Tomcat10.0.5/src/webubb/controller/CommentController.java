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

public class CommentController extends HttpServlet {

    public CommentController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        DBManager dbManager = new DBManager();

        var comments = dbManager.getCommentsOfTopic(Integer.parseInt(id));
        JSONArray jsonAssets = new JSONArray();
        for(int i = 0; i< comments.size();i++){
            JSONObject jObj = new JSONObject();
            jObj.put("id", comments.get(i).getId());
            jObj.put("text", comments.get(i).getText());
            jObj.put("ownername",comments.get(i).getOwnerName());
            jsonAssets.add(jObj);
        }

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonAssets.toJSONString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter("text");
        String ownername = request.getParameter("ownername");
        String topicId = request.getParameter("topicId");

        DBManager dbManager = new DBManager();
        dbManager.addComment(text,ownername, Integer.parseInt(topicId));

        request.getRequestDispatcher("topic.jsp").include(request,response);

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commentId = request.getParameter("id");
        DBManager dbManager = new DBManager();
        dbManager.deleteComment(Integer.parseInt(commentId));
    }

    }