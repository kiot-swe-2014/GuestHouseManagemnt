/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;
    private JLabel lblErrorMessage;

    public LoginForm() {
        // Setup the frame
        setTitle("Login - Guest House Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        setResizable(false);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));  // Light Gray Background
        add(panel);

        // Title
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(150, 20, 100, 30);
        panel.add(lblTitle);

        // Username Label and TextField
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 100, 25);
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 80, 200, 25);
        panel.add(txtUsername);

        // Password Label and PasswordField
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 120, 100, 25);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 120, 200, 25);
        panel.add(txtPassword);

        // Error message label (initially hidden)
        lblErrorMessage = new JLabel();
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setBounds(50, 160, 300, 25);
        lblErrorMessage.setVisible(false);
        panel.add(lblErrorMessage);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 200, 150, 40);
        btnLogin.setBackground(new Color(34, 193, 195));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get username and password
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                // Validate input
                if (validateLogin(username, password)) {
                    // Proceed to Admin Dashboard
                    new AdminDashboardForm();
                    dispose(); // Close login form
                } else {
                    lblErrorMessage.setText("Invalid username or password.");
                    lblErrorMessage.setVisible(true);
                }
            }
        });
        panel.add(btnLogin);

        // Register Button (Redirect to Registration Form)
        btnRegister = new JButton("Register");
        btnRegister.setBounds(210, 200, 150, 40);
        btnRegister.setBackground(new Color(255, 99, 71)); // Tomato color
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Registration Form
                new RegistrationForm();
                dispose(); // Close login form
            }
        });
        panel.add(btnRegister);

        // Display the login form
        setVisible(true);
    }

    private boolean validateLogin(String username, String password) {
        // Simple username validation: must be alphabetic and not empty
        if (username.isEmpty() || !username.matches("[a-zA-Z]+")) {
            return false;
        }

        // Password validation: must contain at least 1 lowercase, 1 uppercase, 1 digit, and 1 special character
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
