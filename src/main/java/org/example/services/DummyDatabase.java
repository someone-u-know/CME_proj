package org.example.services;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.entity.User;
import java.io.*;
import java.util.*;

public class DummyDatabase {
    private static final String CSV_FILE = "/newcsv.csv"; // Relative to src/main/resources

    // Load users
    public void createDB(SocialNetwork network) {
        List<User> users = loadUsersFromCSV();
        for (User user : users) {
            System.out.println(user.getRole());
            user.setId("user_" + UUID.randomUUID());
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

            CsvToBean<User> ctb = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)  // Maps each CSV row to a `User` object.
                    .withIgnoreLeadingWhiteSpace(true) // Trims leading whitespace in CSV fields.
                    .build();

            return ctb.parse();
        } catch (Exception e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
