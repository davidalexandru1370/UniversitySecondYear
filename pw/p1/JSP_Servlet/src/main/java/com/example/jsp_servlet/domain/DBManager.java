package com.example.jsp_servlet.domain;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBManager {
    public DBManager() {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public User getUserFromName(String username) {
        User user = null;
        String statement = "select * from users where username='" + username + "'";
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "George");
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            if (rs.next())
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("all_time_best"));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return user;
    }

    public User authenticate(String username, String password) {
        User user = getUserFromName(username);
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    public List<Question> getNQuestions(int howMany) {
        String statement = "select * from questions order by random() limit " + howMany;
        List<Question> questions = new ArrayList<>();
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "George");
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            while (rs.next())
                questions.add(new Question(rs.getInt("id"), rs.getString("question")));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return questions;
    }

    public List<Answer> AnswersOfQuestion(String question) {
        List<Answer> answers = new ArrayList<>();
        String statement = "select answers.* from answers join questions on answers.questionid = questions.id where questions.question = '" + question + "'";
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "George");
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            while (rs.next())
                answers.add(new Answer(rs.getInt("id"), rs.getInt("questionid"), rs.getBoolean("correctness"), rs.getString("answer")));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return answers;
    }

    public boolean IsAnswerCorrect(int id) {
        Answer answer = null;
        String statement = "select * from answers where id='" + id + "'";
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "George");
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {
            if (rs.next())
                answer = new Answer(rs.getInt("id"), rs.getInt("questionid"), rs.getBoolean("correctness"), rs.getString("answer"));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return answer.getCorrectness();
    }

    public void setNewBest(String username, int newBest)
    {
        User user = null;
        String statement = "update users set all_time_best = "+newBest+" where username='" + username + "'";
        try (var connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsp", "postgres", "George");
             var preparedStatement = connection.prepareStatement(statement);
             var rs = preparedStatement.executeQuery()) {

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
