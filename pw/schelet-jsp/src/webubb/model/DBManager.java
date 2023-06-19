package webubb.model;

import webubb.domain.Asset;
import webubb.domain.Entity;
import webubb.domain.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by forest.
 */
public class DBManager {
    private Statement stmt;
    private final String databaseName = "exam";
    private final String connectionString = "jdbc:mysql://localhost:3306/" + databaseName;
    private final String username = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectionString, username, password);
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("Error when trying to connect:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            if (rs.next()) {
                return true;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from entities";

        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                entities.add(new Entity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    public boolean add(String name, String date){
//        int topicId = this.getTopicId(topicName);
//
//        // if the topic does not exist, we add it
//        if(topicId == 0){
//            this.addTopic(topicName);
//            topicId = this.getTopicId(topicName);
//        }

        // add
        String sql = "INSERT INTO entities(name, date) VALUES ('"
                + name + "', '" + date + "')";

        int affectedRows = 0;
        try {
            affectedRows = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows > 0;
    }

    public boolean delete(int id){
        String sql = "DELETE FROM entities WHERE id = " + id;

        int affectedRows = 0;
        try {
            affectedRows = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows > 0;
    }

    public boolean update(int id, String name, String date) {
//        int topicId = this.getTopicId(topicName);
//
//        // if the topic does not exist, we add it
//        if(topicId == 0){
//            this.addTopic(topicName);
//            topicId = this.getTopicId(topicName);
//        }

        String updateSql = "update entities set " +
                "name='" + name + "'" +
                ", date='" + date + "'" +
                " where id=" + id;

        int affectedRows = 0;
        try {
            affectedRows = stmt.executeUpdate(updateSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows > 0;
    }

    public ArrayList<Entity> filter(String name) {
        ArrayList<Entity> entities = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from entities where name like '%" + name + "%'";

        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                entities.add(new Entity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDate("date")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}