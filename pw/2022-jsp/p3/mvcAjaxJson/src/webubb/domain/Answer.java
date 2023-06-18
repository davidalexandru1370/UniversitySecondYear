package webubb.domain;

public class Answer {
    private int questionId;
    private String answer;

    public Answer(int questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer() {

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
