package webubb.domain;

public class User {
    private int id;
    private String username;
    private int age;
    private String skills;

    public User(int id, String username, int age, String skills) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.skills = skills;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}