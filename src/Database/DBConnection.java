/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/guesthouse"; // Ensure the database name is correct
    private static final String USER = "root"; // Default username for AMPPS MySQL
    private static final String PASSWORD = "mysql"; // Default password for AMPPS MySQL is empty

    public static Connection getConnection() throws SQLException {
        try {
            // Attempt to establish a connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            // Print the exception details
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Database connected successfully.");
            }
        } catch (SQLException e) {
            // Handle and print error if connection fails
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
