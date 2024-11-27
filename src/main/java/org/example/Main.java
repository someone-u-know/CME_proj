package org.example;
import java.util.*;
import lombok.*;

import org.example.entity.Session;
import org.example.entity.User;
import org.example.services.DummyDatabase;
import org.example.services.SocialNetwork;

/*
 * This is the Main class to demonstrate the Social Network application.
 * It provides functionality for both Admin and User roles,
 * including login, registration, and various operations.
 * What I have done here is break the whole flow into functions.
 * So the chaos of switch case inside switch case is formatted effeciently.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        DummyDatabase db = new DummyDatabase();

        // Initialize the database
        db.createDB(network);

        boolean exit = false;

        while (!exit) {
            // Main menu for selecting role
            System.out.println("Enter as: 1. Admin 2. User (enter the number) 3. Logout");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear input buffer

            switch (choice) {
                case 1:
                    handleAdminFlow(sc, network);
                    break;

                case 2:
                    handleUserFlow(sc, network);
                    break;

                case 3:
                    // Exit the application
                    exit = true;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

        sc.close();
    }

    /*
     * Handles the Admin flow, including login and admin-specific operations.
     */
    private static void handleAdminFlow(Scanner sc, SocialNetwork network) {
        System.out.println("Enter your name:");
        String name = sc.nextLine();
        System.out.println("Enter your password:");
        String password = sc.nextLine();

        network.login(name, password);

        if (network.getSession().isLoggedIn() && network.getSession().getRole().equals("admin")) {
            boolean adminFunc = false;

            while (!adminFunc) {
                System.out.println("Admin Name: " + network.getSession().getUserName());
                System.out.println("Admin ID: " + network.getSession().getUserId());
                System.out.println("\nOptions:\n1. View Full User List\n2. Add User\n3. Delete User\n4. Logout");
                int adminChoice = sc.nextInt();
                sc.nextLine();

                switch (adminChoice) {
                    case 1:
                        // View full user list
                        //acccesses the network using getUsersbyId because ID is the only unique parameter
                        System.out.println("Displaying all list users:");
                        for (User user : network.getUsersById().values()) {
                            System.out.println("Id:" + user.getId() + "\n name:" + user.getUsername() + "\nAge" + user.getAge());
                        }
                        break;

                    case 2:
                        // Add a new user
                        System.out.println("Enter username:");
                        String newUsername = sc.nextLine();
                        System.out.println("Enter password:");
                        String newPassword = sc.nextLine();
                        System.out.println("Enter role (user/admin):");
                        String role = sc.nextLine();
                        network.registerUser(newUsername, newPassword, role);
                        System.out.println("User added successfully.");
                        break;

                    case 3:
                        // Delete a user
                        System.out.println("Enter the user ID to delete:");
                        String delid = sc.nextLine();
                        network.deleteUser(delid);
                        System.out.println("User deleted successfully."); //called from main function also so 2 times display
                        break;

                    case 4:
                        // Logout
                        network.logout();
                        adminFunc = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Admin verification failed. Returning to the main menu.");
        }
    }

    /*
     * Handles the User flow, including login, registration, and user-specific operations.
     */
    private static void handleUserFlow(Scanner sc, SocialNetwork network) {
        System.out.println("Enter as: 1. Registered User 2. New User");
        int userChoice = sc.nextInt();
        sc.nextLine();
        // Clear input buffer

        switch (userChoice) {

            case 1:
                // Handle registered user login
                System.out.println("Enter username:");
                String username = sc.nextLine();
                System.out.println("Enter password:");
                String password = sc.nextLine();

                network.login(username, password);

                if (network.getSession().isLoggedIn()) {
                    System.out.println("User logged in successfully.");
                    handleUserOperations(sc, network);
                } else {
                    System.out.println("Login failed. Returning to the main menu.");
                }
                break;

            case 2:
                // Register a new user
                System.out.println("Enter username:");
                String newUser = sc.nextLine();
                System.out.println("Enter password:");
                String newPassword = sc.nextLine();
                network.registerUser(newUser, newPassword, "user");
                System.out.println("User registered successfully.");
                break;

            default:
                System.out.println("Invalid choice. Returning to the main menu.");
                break;
        }
    }

    /*
     * Handles operations for logged-in users, such as viewing profiles and adding friends.
     */
    private static void handleUserOperations(Scanner sc, SocialNetwork network) {
        boolean userFunc = false;

        while (!userFunc) {
            System.out.println("User Name: " + network.getSession().getUserName());
            System.out.println("\nUser Options:\n1. View Profile\n2. Search Users\n3. Add Friends\n4. Update Details\n5. Logout");
            int userAction = sc.nextInt();
            sc.nextLine();

            switch (userAction) {
                case 1:
                    // View user profile

                    Session cs = network.getSession();
                    String uid = cs.getUserId();
                    User user = network.getUsersById().get(uid);
                    System.out.println("Profile:");
                    System.out.println("Name: " + user.getUsername());
                    System.out.println("Age: " + user.getAge());
                    System.out.println("Friends: " + user.getFriends());
                    System.out.println("Hobbies: " + user.getHobbies());
                    break;

                case 2:
                    // Search for users
                    handleUserSearch(sc, network);
                    break;

                case 3:
                    // Add friends
                    System.out.print("Enter the user id of the friend to add: ");
                    String idUsername = sc.nextLine();
                    User loggedInUser = network.getUsersById().get(network.getSession().getUserId());
                    User friend = network.getUsersById().get(idUsername);

                    if (friend!=null){
                        loggedInUser.addFriend(String.valueOf(friend.getId()));
                        System.out.println(idUsername+ "added to friend list");
                    }else{
                        System.out.println(idUsername + "does not exist ");
                    }
                    break;

                case 4:
                    // Update user details
                    handleUserUpdate(sc, network);
                    break;

                case 5:
                    // Logout
                    network.logout();
                    userFunc = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    /*
     * Handles user search functionality based on name, age, or hobbies.
     */
    private static void handleUserSearch(Scanner sc, SocialNetwork network) {
        System.out.println("Search Options:\n1. By Name\n2. By Age\n3. By Hobbies");
        int searchOption = sc.nextInt();
        sc.nextLine(); // Clear input buffer

        switch (searchOption) {
            case 1:
                System.out.println("Enter the name to search:");
                String name = sc.nextLine();
                network.searchUserByName(name);
                break;

            case 2:
                System.out.println("Enter the age to search:");
                int age = sc.nextInt();
                network.searchUserByAge(age);
                break;

            case 3:
                System.out.println("Enter hobbies (comma-separated):");
                String hobbiesInput = sc.nextLine();
                Set<String> hobbies = new HashSet<>(Arrays.asList(hobbiesInput.split(",")));
                network.searchUserByHobbies(hobbies);
                break;

            default:
                System.out.println("Invalid search option.");
                break;
        }
    }

    /*
     * Handles updating user details like name, age, or hobbies.
     */
    private static void handleUserUpdate(Scanner sc, SocialNetwork network) {
        User currentUser = network.getUsersById().get(network.getSession().getUserId());
        System.out.println("Update Options:\n1. Name\n2. Age\n3. Hobbies");
        int updateChoice = sc.nextInt();
        sc.nextLine(); // Clear input buffer

        switch (updateChoice) {
            case 1:
                System.out.println("Enter new name:");
                String newName = sc.nextLine();
                currentUser.setUsername(newName);
                System.out.println("Name updated successfully.");
                break;

            case 2:
                System.out.println("Enter new age:");
                int newAge = sc.nextInt();
                currentUser.setAge(newAge);
                System.out.println("Age updated successfully.");
                break;

            case 3:
                System.out.println("Enter new hobbies (comma-separated):");
                String newHobbies = sc.nextLine();
                Set<String> hobbies = new HashSet<>(Arrays.asList(newHobbies.split(",")));
                currentUser.setHobbies(hobbies);
                System.out.println("Hobbies updated successfully.");
                break;

            default:
                System.out.println("Invalid update option.");
                break;
        }
    }
}




