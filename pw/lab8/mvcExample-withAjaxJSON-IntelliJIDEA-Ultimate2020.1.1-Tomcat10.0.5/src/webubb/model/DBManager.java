package webubb.model;

import webubb.domain.Asset;
import webubb.domain.Comment;
import webubb.domain.Topic;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private Statement stmt;
    private Connection connection;
    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "postgres");
            stmt = connection.createStatement();
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username+" "+password);
        try {
            String statement = "select * from users where name='"+username+"' and password='"+password+"'";
            var preparedStatement = connection.prepareStatement(statement);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                u = new User(rs.getString("name"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void addTopic(String title, String content){
        ResultSet rs;
        Topic resultTopic = null;
        try{
            String statement = "insert into topic(title,content) values('"+title+"','"+content+"')";
            var preparedStatement = connection.prepareStatement(statement);
            rs = preparedStatement.executeQuery();
            rs.close();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public List<Topic> getAllTopics(){
        String statement = "SELECT * FROM topic";
        List<Topic> result = new ArrayList<>();

        try{
            var preparedStatement = connection.prepareStatement(statement);
            var rs = preparedStatement.executeQuery();
            while(rs.next()){
                result.add(new Topic(rs.getInt("id"), rs.getString("title"),rs.getString("content")));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return result;
    }

    public Topic getTopicById(int id){
        String statement = "SELECT * FROM topic where id=" + id;
        Topic topic = null;
        try{
            var preparedStatement = connection.prepareStatement(statement);
            var rs = preparedStatement.executeQuery();
            while(rs.next()){
                topic = new Topic(rs.getInt("id"),rs.getString("title"),rs.getString("content"));
            }
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }

        return topic;
    }

    public List<Comment> getCommentsOfTopic(int topicId){
        String statement = "select * from comments where topicid=" + topicId;
        List<Comment> result = new ArrayList<>();
        try{
            var preparedStatement = connection.prepareStatement(statement);
            var rs = preparedStatement.executeQuery();
            while(rs.next()){
                result.add(new Comment(rs.getInt("id"),rs.getString("text"),rs.getString("ownername"), rs.getInt("topicid")));
            }
        }
        catch(SQLException exception){

        }
            return result;
    }

    public void  addComment(String text, String ownername,int topicId){
        ResultSet rs;
        try{
            String statement = "insert into comments(text,ownername,topicId) values('"+text+"','"+ownername+"','" +topicId+"')";
            var preparedStatement = connection.prepareStatement(statement);
            rs = preparedStatement.executeQuery();
            rs.close();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void deleteComment(int commentId){
        try{
            String statement = "delete from comments where id=" + commentId;
            var preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeQuery();
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
    }


}