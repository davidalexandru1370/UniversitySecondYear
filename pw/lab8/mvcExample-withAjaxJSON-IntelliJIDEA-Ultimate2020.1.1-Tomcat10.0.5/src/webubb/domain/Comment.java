package webubb.domain;

public class Comment {
    private int id;
    private String text;
    private int topicId;
    private String ownerName;

    public Comment(int id, String text, String ownerName, int topicId) {
        this.id = id;
        this.text = text;
        this.ownerName = ownerName;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
