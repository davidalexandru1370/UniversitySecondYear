package webubb.domain;

public class Content {
    private int id;
    private String date;
    private String title;
    private String description;
    private String url;
    private int userId;

    public Content(int id, String date, String title, String description, String url, int userId) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
