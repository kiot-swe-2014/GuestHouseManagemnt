package guesthousemanagementsystem;

import Database.DBConnection;
import gui.LoginForm;
import javax.swing.*;
import java.sql.SQLException;

public class GuestHouseManagementSystem {

    public static void main(String[] args) {
        try {
            // Initialize the database connection directly
            DBConnection.getConnection();  // Get the connection
            
            System.out.println("Database connected successfully.");
            
            // Launch the LoginForm as the entry point of the system
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new LoginForm();  // Open the login form
                }
            });
        } catch (SQLException e) {
            // Catch and print any SQL exceptions that may occur during database initialization
            System.out.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

