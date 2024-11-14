package org.example.entity;


public class Session {
    private boolean loggedIn;
    private String userId;
    private String userName;
    private String role;

    public Session() {
        this.loggedIn = false;
    }


    public void login(String userId, String userName, String role) {
        this.loggedIn = true;
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    public void logout() {
        this.loggedIn = false;
        this.userId = null;
        this.userName = null;
        this.role = null;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
