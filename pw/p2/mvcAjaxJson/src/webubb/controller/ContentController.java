package webubb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import webubb.domain.Content;
import webubb.domain.ContentDto;
import webubb.domain.User;
import webubb.model.DBManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContentController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = br.readLine();
        ContentDto[] contents = objectMapper.readValue(json,ContentDto[].class);
         request.getParameter("content");

        int userId = ((User)request.getSession().getAttribute("user")).getId();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        DBManager dbManager = new DBManager();
        for(ContentDto contentDto: contents){
            dbManager.addContent(now.toString(),contentDto.getTitle(),contentDto.getDescription() ,contentDto.getUrl(),userId);


        }
    }
}