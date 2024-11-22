/* package org.example;
import java.util.*;
import lombok.*;

import org.example.entity.User;
import org.example.services.DummyDatabase;
import org.example.services.SocialNetwork;
import org.example.entity.Session;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        User obj = new User("adarsh", "adarsh1911", "user");
        Scanner sc = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        DummyDatabase Db =  new DummyDatabase();
        Db.createDB(network);
//        network.registerUser("adarsh", "adarsh1911", "user");
        boolean exit = false;
        DummyDatabase db = new DummyDatabase();
        while (!exit){

            // starting or entering into the flow
            System.out.println("Enter as an 1.Admin or 2.User (enter the number) 3. logout");
            int choice = sc.nextInt();
            sc.nextLine(); // keeps excepting the input buffer

            switch (choice){
                case 1:
                    //admin flow
                    System.out.println("Enter your name");
                    String n = sc.nextLine();
                    System.out.println("Enter your password ");
                    String pwd = sc.nextLine();
                    network.login(n,pwd);
                    if(network.getSession().isLoggedIn() && network.getSession().getRole().equals("admin")){
                        boolean adminfunc= false ;
                        while (!adminfunc){

                            System.out.println("Admin name: "+ network.getSession().getUserName()+"Admin ID" + network.getSession().getUserId());
                            System.out.println("\n Options: (choose S.no.) \n 1.view full user list \n 2.Add user \n 3. Delete User \n 4. Logout");
                            int adminchoice = sc.nextInt();
                            sc.nextLine();

                            switch(adminchoice){

                                case 1:
                                    //view full list
                                    System.out.println("displaying all list of users");

                                    for (User user : network.getUsersById().values()) {
                                        System.out.println("Id:" + user.getId() + "name:" + user.getUsername() + "Age" + user.getAge());
                                    }

                                    break;

                                case 2:

                                    //Add user in the network
                                    System.out.println("Enter username");
                                    String newuser = sc.nextLine();
                                    System.out.println("Enter pwd");
                                    String newuserpwd = sc.nextLine();
                                    System.out.println("Enter ROLE (user/admin)");
                                    String newuserrole= sc.nextLine();
                                    network.registerUser(newuser,newuserpwd,newuserrole);
                                    break;

                                case 3:

                                    //delete user
                                    System.out.println("enter the user id to be deleted");
                                    String delid =sc.nextLine();
                                    network.deleteUser(delid);
                                    break;

                                case 4:
                                    network.logout();
                                    adminfunc= true;
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }


                        }

                    }
                    else{
                        System.out.println("Admin verification failed, returning to main menu.");
                    }
                    break;

                case 2:

                    //user flow
                    System.out.println("Enter as 1. Registered user or 2. New user (enter no.)");
                    int userchoice = sc.nextInt();
                    sc.nextLine();

                    switch (userchoice){
                        case 1:
                            //registered user
                            System.out.println("enter username");
                            String uname = sc.nextLine();
                            System.out.println("enter password");
                            String upwd = sc.nextLine();

                            network.login(uname,upwd);
                            if(network.getSession().isLoggedIn()){
                                System.out.println("user loged in successfully");

                                boolean userfunc = false;
                                while (!userfunc){
                                    System.out.println("user name:" + network.getSession().getUserName());
                                    System.out.println("\nUser Options:\n1. View Profile\n2. Search Users\n3. Add friends \n 4.Update details \n 5.Logout");
                                    int useraction = sc.nextInt();
                                    sc.nextLine();

                                    switch(useraction){
                                        case 1:
                                            // Display user profile (name, gender, age, friends, etc.)
                                            User user = network.getUsersByUsername().get(network.getSession().getUserName());
                                            System.out.println("Profile:");
                                            System.out.println("Name" + user.getUsername());
                                            System.out.println("Age: " + user.getAge());
                                            System.out.println("Friends: " + user.getFriends());
                                            System.out.println("Hobbies: " + user.getHobbies());
                                            break;
                                        case 2:
                                            System.out.println("\n Options : \n 1.searchbyname \n 2.searchbyage \n 3.searchbyhobbies");
                                            int usersearchop = sc.nextInt();
                                            sc.nextLine();

                                            switch(usersearchop){
                                                case 1:
                                                    System.out.println("Enter the name to be searched");
                                                    String searchname = sc.nextLine();
                                                    network.searchUserByName(searchname);
                                                    break;
                                                case 2:
                                                    System.out.println("enter the age to be searched");
                                                    int searchage = sc.nextInt();
                                                    network.searchUserByAge(searchage);
                                                    break;
                                                case 3:
                                                    System.out.println("Enter hobbies (comma seperated- small case");
                                                    String searchhobbies = sc.nextLine();
                                                    Set<String> hobbies = new HashSet<>(Arrays.asList(searchhobbies.split(",")));
                                                    network.searchUserByHobbies(hobbies);
                                                    break;
                                            }

                                            break;
                                            
                                        case 3:

                                            //add friends
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

                                            //update details
                                            User currentuser = network.getUsersById().get(network.getSession().getUserId());
                                            System.out.println("What would you like to update");
                                            System.out.println("1. Name \n 2. Age \n 3. Hobbies");
                                            int updatechoice = sc.nextInt();
                                            sc.nextLine();

                                            switch(updatechoice){
                                                case 1:
                                                    System.out.println("Enter new name");
                                                    String nn = sc.nextLine();
                                                    currentuser.setUsername(nn);
                                                    System.out.println("updated");
                                                    break;
                                                case 2:
                                                    System.out.print("Enter new age: ");
                                                    int newAge = sc.nextInt();
                                                    currentuser.setAge(newAge);
                                                    System.out.println("Age updated successfully.");
                                                    break;
                                                case 3:
                                                    System.out.print("Enter new hobbies (comma-separated): ");
                                                    String newHobbies = sc.nextLine();
                                                    Set<String> hobbies = new HashSet<>(Arrays.asList(newHobbies.split(",")));
                                                    currentuser.setHobbies(hobbies);
                                                    System.out.println("Hobbies updated successfully.");
                                                    break;
                                                default:
                                                    System.out.println("Invalid update choice.");


                                            }
                                            break;

                                        case 5:
                                            network.logout();
                                            userfunc = true;
                                            break;
                                        default:
                                            System.out.println("Invalid choice, please try again.");
                                            break;
                                    }


                                }
                            }
                        case 2:
                            // new user , register the user into the network
                            System.out.println("enter name ");
                            String newusername = sc.nextLine();
                            System.out.println("enter password");
                            String newpassword = sc.nextLine();
                            network.registerUser(newusername,newpassword,"user");
                            break;
                        default:
                            //write kuch bhi
                            System.out.println("invalid choice");
                    }
                    exit=true;
                    break;
                case 3:
                    exit= true;
                    System.out.println("bye bye");
                    break;
                default:
                    System.out.println("invalid input");
                    break;

            }

        }
        sc.close();
    }
}

 */

package org.example;
import java.util.*;
import lombok.*;

import org.example.entity.User;
import org.example.services.DummyDatabase;
import org.example.services.SocialNetwork;

/*
 * This is the Main class to demonstrate the Social Network application.
 * It provides functionality for both Admin and User roles,
 * including login, registration, and various operations.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        DummyDatabase db = new DummyDatabase();

        // Initialize the database and populate the network
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
                        System.out.println("Displaying all list users:");
                        for (User user : network.getUsersById().values()) {
                            System.out.println("Id:" + user.getId() + "name:" + user.getUsername() + "Age" + user.getAge());
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
        sc.nextLine(); // Clear input buffer

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
            sc.nextLine(); // Clear input buffer

            switch (userAction) {
                case 1:
                    // View user profile
                    User user = network.getUsersByUsername().get(network.getSession().getUserName());
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




