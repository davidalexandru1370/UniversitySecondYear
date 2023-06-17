package webubb.model;

import webubb.domain.Asset;
import webubb.domain.Project;
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
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projectManagement", "postgres", "postgres");
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

    public User authenticate(String username) {
        ResultSet rs;
        User u = null;
        //System.out.println(username + " " + password);
        try {
            rs = stmt.executeQuery("select * from softwaredeveloper where name= "  + quote(username)  );
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),rs.getString("skills"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<Asset> getUserAssets(int userid) {
        ArrayList<Asset> assets = new ArrayList<Asset>();
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select * from assets where userid=" + userid);
            while (rs.next()) {
                assets.add(new Asset(
                        rs.getInt("id"),
                        rs.getInt("userid"),
                        rs.getString("description"),
                        rs.getInt("value")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    public ArrayList<Project> getAllProjects(){
        ArrayList<Project> projects = new ArrayList<>();

        ResultSet resultSet;

        try{
            resultSet = stmt.executeQuery("select * from project");
            while(resultSet.next()){
                projects.add(new Project(
                        resultSet.getInt("id"),
                        resultSet.getInt("projectmanagerid"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("members")));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

        return projects;
    }

    public boolean updateAsset(Asset asset) {
        int r = 0;
        try {
            r = stmt.executeUpdate("update assets set description='" + asset.getDescription() + "', value=" + asset.getValue() +
                    " where id=" + asset.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (r > 0) return true;
        else return false;
    }

}