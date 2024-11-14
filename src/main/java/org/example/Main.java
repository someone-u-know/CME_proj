package org.example;
import java.sql.SQLOutput;
import java.util.*;

import org.example.entity.User;
import org.example.services.DummyDatabase;
import org.example.services.SocialNetwork;
import org.example.entity.Session;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User obj = new User("adarsh", "adarsh1911", "user");
        Scanner sc = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        network.registerUser("adarsh", "adarsh1911", "user");
        boolean exit = false;
        DummyDatabase db = new DummyDatabase();


        while (!exit){

            // starting or entering into the flow
            System.out.println("Enter as an 1.Admin or 2.User (enter the number)");
            int choice = sc.nextInt();
            sc.nextLine(); // keeps excepting the input buffer

            switch (choice){
                case 1:
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
                                    System.out.println("Enter Id");
                                    String newuserid = sc.nextLine();
                                    System.out.println("Enter ROLE (user/admin)");
                                    String newuserrole= sc.nextLine();
                                    network.registerUser(newuser,newuserid,newuserrole);
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
                            }


                        }

                    }
                    else{
                        System.out.println("Admin verification failed, returning to main menu.");
                    }

                case 2:
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
                                                    System.out.println("enter the agegroup to be searched");
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

                                            //Add friends
                                            System.out.println("Enter the na");
                                            break;










                                    }
                                }
                            }





                    }
            }



        }






    }
}



