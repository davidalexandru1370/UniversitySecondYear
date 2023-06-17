package com.example.jsp_servlet.domain;

public class Answer {
    private final int id;
    private final int questionId;
    private final boolean correctness;
    private final String answer;

    public Answer(int id, int questionId, boolean correctness, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.correctness = correctness;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public boolean getCorrectness() {
        return correctness;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "answer{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", correctness='" + correctness + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
