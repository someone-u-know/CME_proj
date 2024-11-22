package org.example.services;
import org.example.entity.Session;
import org.example.entity.User;
import lombok.Data;


import java.util.*;

/*
 * Represents a social network service with user management and session handling.
 */

@Data
public class SocialNetwork {

    private Map<String, User> usersById;
    private Map<String, User> usersByUsername;
    private Map<Integer, List<User>> usersByAge;
    private Map<String, List<User>> usersByHobby;
    private Session session;

    DummyDatabase obj = new DummyDatabase();

    /**
     * Initializes the social network with empty user data and a session.
     */
    public SocialNetwork() {
        usersById = new HashMap<>();
        usersByUsername = new HashMap<>();
        usersByAge = new HashMap<>();
        usersByHobby = new HashMap<>();
        session = new Session();
    }

    // Getters and Setters
    //implemented with lombok.



    /*
     * Registers a new user.
     */
    public void registerUser(String username, String password, String role) {
        if (session.isLoggedIn()) {
            System.out.println("Please log out to register a new user.");
            return;
        }
        User user = new User(username, password, role);
        usersById.put(user.getId(), user);
        usersByUsername.put(username, user);
        System.out.println("User registered with ID: " + user.getId());
        obj.storeData(user);


    }

    /**
     * Overloaded method to register a user object.
     */
    public void registerUser(User u) {
        if (session.isLoggedIn()) {
            System.out.println("Please log out to register a new user.");
            return;
        }
        usersById.put(u.getId(), u);
        usersByUsername.put(u.getUsername(), u);
        System.out.println("User registered with ID: " + u.getId());
    }

    /**
     * Logs in a user with their username and password.
     */
    public void login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user != null && user.getPassword().equals(password)) {
            session.login(user.getId(), user.getUsername(), user.getRole());
            System.out.println("Login successful. Welcome, " + user.getUsername());
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        if (session.isLoggedIn()) {
            System.out.println("Goodbye, " + session.getUserName());
            session.logout();
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    /**
     * Deletes a user (admin only).
     */
    public void deleteUser(String userId) {
        if (!session.isLoggedIn() || !"admin".equals(session.getRole())) {
            System.out.println("Only admins can delete users.");
            return;
        }
        User user = usersById.remove(userId);
        if (user != null) {
            usersByUsername.remove(user.getUsername());
            System.out.println("User deleted sucessfully.");
        } else {
            System.out.println("User ID not found.");
        }
    }

    /**
     * Searches for users by name.
     */
    public void searchUserByName(String name) {
        if (!session.isLoggedIn()) {
            System.out.println("Please log in to perform search operations.");
            return;
        }
        System.out.println("Users with name '" + name + "':");
        for (User user : usersByUsername.values()) {
            if (user.getUsername().equalsIgnoreCase(name)) {
                System.out.println("ID: " + user.getId() + ", Role: " + user.getRole());
            }
        }
    }

    /**
     * Searches for users by age.
     */
    public void searchUserByAge(int age) {
        if (!session.isLoggedIn()) {
            System.out.println("Please log in to perform search operations.");
            return;
        }
        System.out.println("Users with age " + age + ":");
        for (User user : usersById.values()) {
            if (user.getAge() != null && user.getAge() == age) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername());
            }
        }
    }

    /**
     * Searches for users by hobbies.
     */
    public void searchUserByHobbies(Set<String> hobbies) {
        if (!session.isLoggedIn()) {
            System.out.println("Please log in to perform search operations.");
            return;
        }
        System.out.println("Users with hobbies " + hobbies + ":");
        for (User user : usersById.values()) {
            if (user.getHobbies() != null && user.getHobbies().containsAll(hobbies)) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername());
            }
        }
    }

    /**
     * Retrieves friends of a user by their ID.
     */
    public void getFriendsOfUser(String userId) {
        if (!session.isLoggedIn()) {
            System.out.println("Please log in to view friends.");
            return;
        }
        User user = usersById.get(userId);
        if (user != null) {
            System.out.println("Friends of " + user.getUsername() + ": " + user.getFriends());
        } else {
            System.out.println("User ID not found.");
        }
    }
}

