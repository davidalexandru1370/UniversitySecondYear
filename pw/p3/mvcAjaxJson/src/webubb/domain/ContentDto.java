package webubb.domain;

public class ContentDto {

    private String title;
    private String description;
    private String url;

    public ContentDto(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public ContentDto(){

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
}
