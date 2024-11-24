package org.example.entity;
import lombok.Data;

/*
 * Represents a session with login and logout functionality to ensure security and authentiication.
 */
@Data
public class Session {

    private boolean loggedIn;
    private String userId;
    private String userName;
    private String role;

    /*
     * Constructs a session, defaulting to a logged-out state.
     */
    public Session() {
        this.loggedIn = false;
    }

    /*
     * Logs in a user by setting their session details.
     */
    public void login(String userId, String userName, String role) {
        this.loggedIn = true;
        this.userId = userId;
        this.userName = userName;
        this.role = role;
    }

    /*
     * Logs out the user and clears session details.
     */
    public void logout() {
        this.loggedIn = false;
        this.userId = null;
        this.userName = null;
        this.role = null;
    }

    /*
     * Checks if the user is logged in.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}

