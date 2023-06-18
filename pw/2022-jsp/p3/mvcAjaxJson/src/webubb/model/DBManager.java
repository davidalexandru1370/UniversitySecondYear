package webubb.model;

import webubb.domain.Question;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp3", "postgres", "postgres");
            stmt = con.createStatement();
        } catch (Exception ex) {
            System.out.println("eroare la connect:" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private String quote(String str){
        return new StringBuilder()
                .append('\'')
                .append(str)
                .append('\'')
                .toString();
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username + " " + password);
        try {
            rs = stmt.executeQuery("select * from users where username= "  + quote(username)  + "and password=" + quote(password));
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("role"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void addContent(String date, String title, String description, String url, int userId){
        try{
            stmt.executeQuery(String.format("insert into content(date,title,description,url,userid) values(%s,%s,%s,%s,%d)",
                    quote(date),
                    quote(title),
                    quote(description),
                    quote(url),
                    userId));
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestions(){
        ArrayList<Question> questions = new ArrayList<>();

        ResultSet resultSet;
        try{
            resultSet = stmt.executeQuery("select * from question");
            while(resultSet.next()){
                questions.add(new Question(resultSet.getInt("id"),
                        resultSet.getString("text"),
                        resultSet.getString("correctanswer"),
                        resultSet.getString("wronganswer")));
            }
            resultSet.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return questions;
    }


}