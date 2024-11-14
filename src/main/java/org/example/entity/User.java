package org.example.entity;
import java.util.*;

public class User {
    private String username;
    private String password;
    private String role;
    private Integer age;
    private Set<String> hobbies;
    private final String id;
    private Set<String> friends;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id = UUID.randomUUID().toString();
        this.hobbies = new HashSet<>();
        this.friends = new HashSet<>();
    }



    public Set<String> getFriends() {
        return friends;
    }

    public void addFriend(String friendId) {
        if(this.id != friendId){
            friends.add(friendId);

        }
        else{
            System.out.println("you cannot add yourself as a friend");
        }
    }


    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public String getId() {
        return id;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
