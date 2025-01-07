/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import Database.UserDB;
import models.User;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginForm() {
        // Frame setup
        setTitle("Login");
        setSize(400, 350);  // Adjusted size for better spacing
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 2, 10, 20));  // Adjusted for better layout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel titleLabel = new JLabel("Login to Your Account", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(41, 128, 185));
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel());  // Empty space to maintain layout

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        usernameField.setText("Enter username");
        usernameField.setForeground(Color.GRAY);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().equals("Enter username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Enter username");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setText("Enter password");
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (new String(passwordField.getPassword()).equals("Enter password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Enter password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> loginUser());
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(52, 152, 219));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(41, 128, 185));
            }
        });
        mainPanel.add(loginButton);

        // Register button (redirect to RegistrationForm)
        registerButton = new JButton("Don't have an account? Register");
        registerButton.setBackground(new Color(46, 204, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 12));
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(e -> redirectToRegistrationForm());
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(46, 204, 113));
            }
        });
        mainPanel.add(registerButton);

        // Adding main panel to the frame
        add(mainPanel);
    }

    // Method to handle login
    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        // Validate the user credentials
        User user = UserDB.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            // User found, login successful
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + user.getFullName() + ".");
            // Proceed to the next window or dashboard (e.g., Guest Dashboard)
            dispose(); // Close the login form
            new GuestDashboardForm(user);  // Open the guest dashboard form
        } else {
            // User not found
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    // Method to redirect to the RegistrationForm
    private void redirectToRegistrationForm() {
        dispose(); // Close the login form
        new RegistrationForm().setVisible(true); // Open the registration form
    }

    // Main method to run the LoginForm
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
