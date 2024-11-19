/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database connection credentials
    private static final String URL = "jdbc:mysql://localhost:3306/guesthouse"; // Replace with your DB URL
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = ""; // Replace with your MySQL password
    
    private static Connection connection = null;

    // Method to initialize the database connection
    public static void initialize() throws SQLException {
        if (connection == null) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establish the connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (ClassNotFoundException e) {
                // Handle the case where the JDBC driver is not found
                System.out.println("MySQL JDBC driver not found.");
                e.printStackTrace();
                throw new SQLException("Unable to load MySQL JDBC driver.", e);
            }
        }
    }

    // Method to get the current database connection
    public static Connection getConnection() {
        return connection;
    }

    // Method to close the database connection
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
