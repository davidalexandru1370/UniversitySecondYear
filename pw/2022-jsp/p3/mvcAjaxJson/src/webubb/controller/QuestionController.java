package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Question;
import webubb.model.DBManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class QuestionController  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();

        response.setContentType("application/json");
        ArrayList<Question> questions = dbManager.getQuestions();
        JSONArray jsonArray = new JSONArray();
        for(Question question: questions){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",question.getId());
            jsonObject.put("text",question.getText());
            jsonObject.put("correctAnswer", question.getCorrectAnswer());
            jsonObject.put("wrongAnswer", question.getWrongAnswer());
            jsonArray.add(jsonObject);
        }

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonArray.toJSONString());
        out.flush();
    }

}
