package org.example.services;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.entity.User;


import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DummyDatabase {
    private static final String CSV_FILE = "/newcsv.csv"; // Relative to src/main/resources

    // Load users from CSV and register them to the SocialNetwork
    public void createDB(SocialNetwork network) {
        List<User> users = loadUsersFromCSV();
        for (User user : users) {
            System.out.println(user.getRole());
            user.setId(String.valueOf("user_"+UUID.randomUUID()));
            network.registerUser(user);
        }
    }

    // Method to load users from a CSV file
    private List<User> loadUsersFromCSV() {
        try (InputStream inputStream = getClass().getResourceAsStream(CSV_FILE)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("CSV file not found in resources: " + CSV_FILE);
            }
            Reader reader = new InputStreamReader(inputStream);

            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return Collections.emptyList();
    }

    // Store the data in some file (method stub for future implementation)
    public void storeData(User user) {



        // Define the data to write (assumes User class has getters for the fields)
        String[] userData = new String[]{
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                // Add other fields here
        };

        try (Writer writer = new FileWriter(CSV_FILE, true); // true for appending
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Write data to CSV
            csvWriter.writeNext(userData);
            System.out.println("User saved to CSV successfully!");

        } catch (IOException e) {
            System.out.println("Error while saving user to CSV: " + e.getMessage());
        }
        // Future implementation for storing data


    }
}
