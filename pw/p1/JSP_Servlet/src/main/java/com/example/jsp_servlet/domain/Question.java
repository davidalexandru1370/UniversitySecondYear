package com.example.jsp_servlet.domain;

public class Question {
    private final int id;
    private final String question;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
