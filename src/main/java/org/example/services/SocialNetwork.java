package org.example.services;
import org.example.entity.Session;
import org.example.entity.User;

import java.util.*;

public class SocialNetwork {
    private Map<String, User> usersById;
    private Map<String, User> usersByUsername;
    private Map<Integer, List<User>> usersByAge;
    private Map<String, List<User>> usersByHobby;
    private Session session;


    public SocialNetwork() {
        usersById = new HashMap<>();
        usersByUsername = new HashMap<>();
        usersByAge = new HashMap<>();
        usersByHobby = new HashMap<>();
        session = new Session();
    }

    public Map<String, User> getUsersById() {
        return usersById;
    }

    public void setUsersById(Map<String, User> usersById) {
        this.usersById = usersById;
    }

    public Map<String, User> getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(Map<String, User> usersByUsername) {
        this.usersByUsername = usersByUsername;
    }

    public Map<Integer, List<User>> getUsersByAge() {
        return usersByAge;
    }

    public void setUsersByAge(Map<Integer, List<User>> usersByAge) {
        this.usersByAge = usersByAge;
    }

    public Map<String, List<User>> getUsersByHobby() {
        return usersByHobby;
    }

    public void setUsersByHobby(Map<String, List<User>> usersByHobby) {
        this.usersByHobby = usersByHobby;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    // Register a new user

    public void registerUser(String username, String password, String role) {
        if (session.isLoggedIn()) {
            System.out.println("Please log out to register a new user.");
            return;
        }

//        if (usersByUsername.containsKey(username)) {
//            System.out.println("Username taken. Please choose a different username.");
//            return;
//        }



        User user = new User(username, password, role);
        usersById.put(user.getId(), user);
        usersByUsername.put(username, user);
        System.out.println("User registered with ID: " + user.getId());
    }

    // Login user
    public void login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user != null && user.getPassword().equals(password)) {
            session.login(user.getId(), user.getUsername(), user.getRole());
            System.out.println("Login successful. Welcome, " + user.getUsername());
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // Logout user
    public void logout() {
        if (session.isLoggedIn()) {
            System.out.println("Goodbye, " + session.getUserName());
            session.logout();
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    // Delete user (admin only)
    public void deleteUser(String userId) {
        if (!session.isLoggedIn() || !session.getRole().equals("admin")) {
            System.out.println("Only admins can delete users.");
            return;
        }
        if (usersById.containsKey(userId)) {
            User user = usersById.remove(userId);
            usersByUsername.remove(user.getUsername());
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User ID not found.");
        }
    }

    // Search by name
    public void searchUserByName(String name) {
        if (!session.isLoggedIn()) {
            System.out.println("Please log in to perform search operations.");
            return;
        }
        System.out.println("Users with name '" + name + "':");
        for (User user : usersByUsername.values()) {
            if (user.getUsername().equalsIgnoreCase(name)) {
                System.out.println("ID: " + user.getId() + ", Role: " + user.getRole());
                System.out.println("Want to add this as your frand?");


            }
        }
    }

    // Search by age
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


    // Search by hobbies
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


    // Get friends of a user by ID
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
