package model;

public class Client {

    private String username;
    private String fullName;

    public Client(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

}
