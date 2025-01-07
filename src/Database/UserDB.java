/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import models.User;
import models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private static final String URL = "jdbc:mysql://localhost:3306/guesthouse"; // Your database URL
    private static final String USER = "root"; // Your database username
    private static final String PASSWORD = "mysql"; // Your database password
    private static Connection connection;

    // Static block to establish the database connection
    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    // Add a new user to the database
    public static boolean addUser(User user) {
        String query = "INSERT INTO users (full_name, username, email, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword()); // Ensure password is hashed if hashing is implemented
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Returns true if the insertion was successful
        } catch (SQLException e) {
            System.err.println("Error adding user to the database.");
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all users from the database
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving users from the database.");
            e.printStackTrace();
        }
        return users;
    }

    // Check if a username exists in the database
    public static boolean usernameExists(String username) {
        String query = "SELECT 1 FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if the username exists
        } catch (SQLException e) {
            System.err.println("Error checking username in the database.");
            e.printStackTrace();
            return false;
        }
    }

    // Check if an email exists in the database
    public static boolean emailExists(String email) {
        String query = "SELECT 1 FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Returns true if the email exists
        } catch (SQLException e) {
            System.err.println("Error checking email in the database.");
            e.printStackTrace();
            return false;
        }
    }

    // Authenticate a user by username and password
    public static User getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password); // Ensure password is hashed if hashing is implemented
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error authenticating user.");
            e.printStackTrace();
        }
        return null; // Return null if authentication fails
    }

    // Update an existing user's details
    public static boolean updateUser(User user) {
        String query = "UPDATE users SET full_name = ?, email = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            System.err.println("Error updating user details.");
            e.printStackTrace();
            return false;
        }
    }

    // Delete a user from the database
    public static boolean deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Returns true if the deletion was successful
        } catch (SQLException e) {
            System.err.println("Error deleting user from the database.");
            e.printStackTrace();
            return false;
        }
    }

    // Fetch user details along with their bookings
    public static User getUserWithBookings(String username) {
        String userQuery = "SELECT * FROM users WHERE username = ?";
        String bookingQuery = "SELECT * FROM bookings WHERE user_id = ?";
        User user = null;

        try (PreparedStatement userStmt = connection.prepareStatement(userQuery)) {
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                user = new User(
                        userRs.getInt("id"),
                        userRs.getString("full_name"),
                        userRs.getString("username"),
                        userRs.getString("email"),
                        userRs.getString("password")
                );

                try (PreparedStatement bookingStmt = connection.prepareStatement(bookingQuery)) {
                    bookingStmt.setInt(1, user.getId());
                    ResultSet bookingRs = bookingStmt.executeQuery();
                    List<Booking> bookings = new ArrayList<>();

                    while (bookingRs.next()) {
                        Booking booking = new Booking(
                                bookingRs.getInt("booking_id"),
                                bookingRs.getInt("user_id"),
                                bookingRs.getInt("room_id"),
                                bookingRs.getDate("check_in_date").toLocalDate(),
                                bookingRs.getDate("check_out_date").toLocalDate(),
                                bookingRs.getString("status"),
                                bookingRs.getString("payment_method"),
                                bookingRs.getString("transaction_number")
                        );
                        bookings.add(booking);
                    }
                    user.setBookings(bookings); // Attach bookings to the user
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user with bookings.");
            e.printStackTrace();
        }

        return user;
    }
}
