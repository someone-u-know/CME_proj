package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Represents a User entity with attributes and basic functionalities.
 * The class provides functionality to manage user data and relationships (like friends).
 */
@Data
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String role;
    private Integer age;
    private Set<String> hobbies;
    private String id;
    private Set<String> friends;

    /**
     * Constructs a User with the specified username, password, and role.
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = "user_" + UUID.randomUUID().toString();
        this.hobbies = new HashSet<>();
        this.friends = new HashSet<>();
    }


    /**
     * Adds a friend's ID to the user's friends list, ensuring the user doesn't add themselves.
     */
    public void addFriend(String friendId) {
        if (!Objects.equals(this.id, friendId)) {
            friends.add(friendId);
        } else {
            System.out.println("You cannot add yourself as a friend");
        }
    }
}

