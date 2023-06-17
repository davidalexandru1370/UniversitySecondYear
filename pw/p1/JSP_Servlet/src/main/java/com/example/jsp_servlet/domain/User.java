package com.example.jsp_servlet.domain;

public class User {
    private final int id;
    private final String username;
    private final String password;

    private final int all_time_best;

    public User(int id, String username, String password, int all_time_best) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.all_time_best = all_time_best;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAll_time_best() {
        return all_time_best;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", all_time_best='" + all_time_best + '\'' +
                '}';
    }
}
