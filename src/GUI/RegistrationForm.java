/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import Database.UserDB;
import models.User;

public class RegistrationForm extends JFrame {
    private JTextField fullNameField, usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    public RegistrationForm() {
        // Frame setup
        setTitle("User Registration");
        setSize(400, 500); // Adjusted for better spacing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2, 10, 15)); // Increased vertical spacing
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label
        JLabel titleLabel = new JLabel("Register", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(41, 128, 185));
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel()); // Empty space to maintain layout

        // Full Name Field
        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameField = new JTextField(20);
        fullNameField.setText("Enter full name");
        fullNameField.setForeground(Color.GRAY);
        fullNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        fullNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (fullNameField.getText().equals("Enter full name")) {
                    fullNameField.setText("");
                    fullNameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (fullNameField.getText().isEmpty()) {
                    fullNameField.setText("Enter full name");
                    fullNameField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(fullNameLabel);
        mainPanel.add(fullNameField);

        // Username Field
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

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setText("Enter email");
        emailField.setForeground(Color.GRAY);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (emailField.getText().equals("Enter email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Enter email");
                    emailField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);

        // Password Field
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

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(41, 128, 185));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.addActionListener(e -> registerUser());
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(52, 152, 219));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(41, 128, 185));
            }
        });
        mainPanel.add(registerButton);

        // Login Button (if user already has an account)
        loginButton = new JButton("Already have an account? Login");
        loginButton.setBackground(new Color(46, 204, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.addActionListener(e -> redirectToLogin());
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(39, 174, 96));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(46, 204, 113));
            }
        });
        mainPanel.add(loginButton);

        // Adding the main panel to the frame
        add(mainPanel);
    }

    private void registerUser() {
        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        // Validate full name (letters and spaces only)
        if (!fullName.matches("^[a-zA-Z ]+$")) {
            JOptionPane.showMessageDialog(this, "Full Name must contain only letters and spaces.");
            return;
        }

        // Validate username (letters and numbers only, no special characters)
        if (!username.matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Username must contain only letters and numbers.");
            return;
        }

        // Validate email format using regular expression
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(this, "Invalid email format.");
            return;
        }

        // Validate password strength (minimum 8 characters, at least 1 number)
        if (password.length() < 8 || !password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long and contain at least one number.");
            return;
        }

        try {
            // Check if the username already exists in the database
            boolean usernameExists = UserDB.usernameExists(username);
            if (usernameExists) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.");
                return;
            }

            // Register user in the database
            User newUser = new User(0, fullName, username, email, password);
            boolean isAdded = UserDB.addUser(newUser);

            if (isAdded) {
                JOptionPane.showMessageDialog(this, "User Registered Successfully!");

                // Redirect to the Guest Dashboard after registration
                dispose(); // Close the registration form
                new GuestDashboardForm(newUser); // Pass the newUser to the dashboard form
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while registering. Please try again.");
        }
    }

    private void redirectToLogin() {
        dispose(); // Close the registration form
        new LoginForm().setVisible(true); // Open the login form
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}


