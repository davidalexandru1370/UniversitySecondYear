package webubb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.Answer;
import webubb.domain.Question;
import webubb.model.DBManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.out;

public class QuestionController  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();

        response.setContentType("application/json");
        ArrayList<Question> questions = dbManager.getQuestions();
        JSONArray jsonArray = new JSONArray();
        for (Question question : questions) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", question.getId());
            jsonObject.put("text", question.getText());
            jsonObject.put("correctAnswer", question.getCorrectAnswer());
            jsonObject.put("wrongAnswer", question.getWrongAnswer());
            jsonArray.add(jsonObject);
        }

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonArray.toJSONString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = br.readLine();
        Answer[] answers = objectMapper.readValue(json, Answer[].class);
        String username = request.getSession().getAttribute("user").toString();
        DBManager dbManager = new DBManager();
        int score = dbManager.addAnswers(username, answers);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("score", score);

        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonObject.toJSONString());
        out.flush();
    }
}