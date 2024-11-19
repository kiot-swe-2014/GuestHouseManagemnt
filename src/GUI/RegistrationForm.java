/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import database.UserDB;

public class RegistrationForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtFullName;
    private JButton btnRegister;
    private JButton btnBack;

    public RegistrationForm() {
        setTitle("Register - Guest House Management");
        setSize(400, 300); // Adjust size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel to hold all components with padding and background color
        JPanel panel = new JPanel();
        panel.setLayout(null); // Null layout for absolute positioning
        panel.setBackground(new Color(255, 255, 255)); 
        add(panel);

        // Title Label
        JLabel lblTitle = new JLabel("User Registration");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(120, 20, 250, 30); // Adjust position
        panel.add(lblTitle);

        // Username Label and TextField
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsername.setBounds(50, 70, 100, 25);
        panel.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBounds(160, 70, 200, 25);
        txtUsername.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));
        panel.add(txtUsername);

        // Password Label and PasswordField
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setBounds(50, 110, 100, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBounds(160, 110, 200, 25);
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));
        panel.add(txtPassword);

        // Confirm Password Label and PasswordField
        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblConfirmPassword.setBounds(50, 150, 150, 25);
        panel.add(lblConfirmPassword);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtConfirmPassword.setBounds(160, 150, 200, 25);
        txtConfirmPassword.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));
        panel.add(txtConfirmPassword);

        // Full Name Label and TextField
        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(new Font("Arial", Font.PLAIN, 16));
        lblFullName.setBounds(50, 190, 100, 25);
        panel.add(lblFullName);

        txtFullName = new JTextField();
        txtFullName.setFont(new Font("Arial", Font.PLAIN, 14));
        txtFullName.setBounds(160, 190, 200, 25);
        txtFullName.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));
        panel.add(txtFullName);

        // Register Button with enhanced styling
        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegister.setBackground(new Color(34, 193, 195));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setBounds(160, 230, 120, 35);
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        panel.add(btnRegister);

        // Back Button with enhanced styling
        btnBack = new JButton("Back to Login");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(255, 99, 71));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(300, 230, 120, 35); // Adjusted position to prevent overlap
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm(); // Open Login form
                dispose(); // Close Registration form
            }
        });
        panel.add(btnBack);

        // Show the form
        setVisible(true);
    }

    // Method to handle registration logic with validation
    private void register() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        String fullName = txtFullName.getText();

        // Username validation: Not empty
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Password validation: At least one uppercase letter, one lowercase letter, and one number
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter, one lowercase letter, and one number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirm password validation
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Call UserDB to register the user
        boolean success = UserDB.registerUser(username, password, fullName);
        if (success) {
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginForm(); // Redirect to login form
            dispose(); // Close Registration form
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
