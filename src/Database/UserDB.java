/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import models.User;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/guesthouse";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to hash the password before storing it in the database
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString(); // Return hashed password
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Return null if hashing fails
        }
    }

    // Method to register a new user
    public static boolean registerUser(String username, String password, String fullName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Check if the username already exists
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement psCheck = connection.prepareStatement(checkQuery)) {
                psCheck.setString(1, username);
                ResultSet rs = psCheck.executeQuery();
                if (rs.next()) {
                    return false; // Username already exists
                }
            }

            // Hash the password before storing
            String hashedPassword = hashPassword(password);
            if (hashedPassword == null) {
                return false; // If hashing fails, return false
            }

            // Insert the new user into the database
            String insertQuery = "INSERT INTO users (username, password, full_name, status, created_at, updated_at) " +
                                 "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement psInsert = connection.prepareStatement(insertQuery)) {
                psInsert.setString(1, username);
                psInsert.setString(2, hashedPassword);  // Store hashed password
                psInsert.setString(3, fullName);
                psInsert.setString(4, "active"); // Default status to "active"
                
                // Use current timestamp for both created_at and updated_at
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                psInsert.setTimestamp(5, timestamp);
                psInsert.setTimestamp(6, timestamp);

                int rowsAffected = psInsert.executeUpdate();
                return rowsAffected > 0; // Return true if registration was successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred
        }
    }

    // Method to authenticate a user
    public static boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {

            // Hash the entered password
            String hashedPassword = hashPassword(password);
            if (hashedPassword == null) {
                return false; // If password hashing fails, return false
            }

            // Query to check username and hashed password
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, hashedPassword); // Compare with hashed password in database
                ResultSet rs = ps.executeQuery();
                return rs.next(); // If user found, authentication is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Authentication failed
        }
    }
}
