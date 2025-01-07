/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Database.UserDB;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ManageUsersForm extends JFrame {

    private JTextField usernameField, fullNameField, emailField;
    private JButton addButton, updateButton, deleteButton, viewButton, backButton;

    public ManageUsersForm() {
        setTitle("Manage Users");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Prevent resizing the window

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(new Color(245, 245, 245)); // Light background color
        add(panel);

        // Labels and fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(15);

        // Action Buttons
        addButton = new JButton("Add User");
        updateButton = new JButton("Update User");
        deleteButton = new JButton("Delete User");
        viewButton = new JButton("View Users");
        backButton = new JButton("Back to Dashboard"); // New Back Button

        // Styling Buttons
        styleButton(addButton, new Color(34, 193, 195));
        styleButton(updateButton, new Color(255, 99, 71));
        styleButton(deleteButton, new Color(255, 69, 0));
        styleButton(viewButton, new Color(60, 179, 113));
        styleButton(backButton, new Color(70, 130, 180)); // Steel Blue for Back Button

        // Button Actions
        addButton.addActionListener(this::addUser);
        updateButton.addActionListener(this::updateUser);
        deleteButton.addActionListener(this::deleteUser);
        viewButton.addActionListener(this::viewUsers);
        backButton.addActionListener(e -> {
            // Navigate back to Admin Dashboard
            new AdminDashboardForm().setVisible(true); // Open Admin Dashboard
            dispose(); // Close the current form
        });

        // Set GridBagConstraints for Labels and Fields
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(fullNameLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(fullNameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(emailLabel, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(emailField, gbc);

        // Set GridBagConstraints for Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(addButton, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(updateButton, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(deleteButton, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(viewButton, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(backButton, gbc); // Adding the Back Button
    }

    // Method to style the buttons for better appearance
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 40));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Add user method
    private void addUser(ActionEvent e) {
        String username = usernameField.getText();
        String fullName = fullNameField.getText();
        String email = emailField.getText();

        if (!username.isEmpty() && !fullName.isEmpty() && !email.isEmpty()) {
            String password = "defaultPassword"; // Default password
            User user = new User(0, fullName, username, email, password); // User instance
            boolean isAdded = UserDB.addUser(user);

            if (isAdded) {
                JOptionPane.showMessageDialog(this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Error adding user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Update user method
    private void updateUser(ActionEvent e) {
        String username = usernameField.getText();
        String fullName = fullNameField.getText();
        String email = emailField.getText();

        if (!username.isEmpty() && !fullName.isEmpty() && !email.isEmpty()) {
            User user = new User(0, fullName, username, email, "updatedPassword");
            boolean isUpdated = UserDB.updateUser(user);

            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Delete user method
    private void deleteUser(ActionEvent e) {
        String username = usernameField.getText();

        if (!username.isEmpty()) {
            boolean isDeleted = UserDB.deleteUser(username);

            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting user!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid username!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // View users method
    private void viewUsers(ActionEvent e) {
        List<User> users = UserDB.getAllUsers();

        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No users available!", "Information", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder userDetails = new StringBuilder();
            for (User user : users) {
                userDetails.append("Username: ").append(user.getUsername()).append("\n")
                        .append("Full Name: ").append(user.getFullName()).append("\n")
                        .append("Email: ").append(user.getEmail()).append("\n\n");
            }
            JOptionPane.showMessageDialog(this, userDetails.toString(), "Users", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Method to clear input fields
    private void clearFields() {
        usernameField.setText("");
        fullNameField.setText("");
        emailField.setText("");
    }

    // Main method to run the form
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageUsersForm().setVisible(true));
    }
}
