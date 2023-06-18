package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import webubb.domain.User;
import webubb.model.DBManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContentController extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DBManager dbManager = new DBManager();
//        response.setContentType("application/json");
//        ArrayList<Content> contests = dbManager.getMostRecentFour();
//
//        JSONArray jsonAssets = new JSONArray();
//        for(Content content: contests){
//            JSONObject jObj = new JSONObject();
//            jObj.put("id", content.getId());
//            jObj.put("date", content.getDate());
//            jObj.put("title",content.getTitle());
//            jObj.put("description",content.getDescription());
//            jObj.put("url", content.getUrl());
//            jObj.put("userid", content.getUserId());
//
//            jsonAssets.add(jObj);
//        }
//
//        PrintWriter out = new PrintWriter(response.getOutputStream());
//        out.println(jsonAssets.toJSONString());
//        out.flush();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String json = br.readLine();
//        ContentDto[] contents = objectMapper.readValue(json,ContentDto[].class);
//         request.getParameter("content");
//
//        int userId = ((User)request.getSession().getAttribute("user")).getId();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//
//        DBManager dbManager = new DBManager();
//        for(ContentDto contentDto: contents){
//            dbManager.addContent(now.toString(),contentDto.getTitle(),contentDto.getDescription() ,contentDto.getUrl(),userId);
//
//
//        }
//    }
}