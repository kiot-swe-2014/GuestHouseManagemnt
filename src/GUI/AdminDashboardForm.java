/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminDashboardForm extends JFrame {

    private JButton btnManageRooms;
    private JButton btnManageUsers; // Button for managing users
    private JLabel lblStatus;
    private JPanel panel;

    public AdminDashboardForm() {
        // Setting up the frame
        setTitle("Admin Dashboard - Guest House Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        // Panel to hold components
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Light gray background
        add(panel);

        // Title
        JLabel lblTitle = new JLabel("Admin Dashboard");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(200, 20, 250, 30);
        panel.add(lblTitle);

        // Status label (Occupancy, Revenue, etc.)
        lblStatus = new JLabel("Status: Loading...");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
        lblStatus.setBounds(200, 70, 250, 25);
        panel.add(lblStatus);

        // Button to manage rooms
        btnManageRooms = new JButton("Manage Rooms");
        btnManageRooms.setFont(new Font("Arial", Font.BOLD, 16));
        btnManageRooms.setBackground(new Color(34, 193, 195)); // Teal color
        btnManageRooms.setForeground(Color.WHITE);
        btnManageRooms.setBounds(200, 120, 200, 40);
        btnManageRooms.setFocusPainted(false);
        btnManageRooms.setBorderPainted(false);
        btnManageRooms.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManageRooms.addActionListener(e -> {
            // Open Room Management Form
            new RoomManagementForm().setVisible(true); // Ensure the new form is visible
            dispose(); // Close the dashboard
        });
        panel.add(btnManageRooms);

        // Button to manage users
        btnManageUsers = new JButton("Manage Users");
        btnManageUsers.setFont(new Font("Arial", Font.BOLD, 16));
        btnManageUsers.setBackground(new Color(60, 179, 113)); // Medium Sea Green
        btnManageUsers.setForeground(Color.WHITE);
        btnManageUsers.setBounds(200, 180, 200, 40);
        btnManageUsers.setFocusPainted(false);
        btnManageUsers.setBorderPainted(false);
        btnManageUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManageUsers.addActionListener(e -> {
            // Open Manage Users Form
            new ManageUsersForm().setVisible(true); // Open Manage Users Form
            dispose(); // Close the dashboard
        });
        panel.add(btnManageUsers);

        // Button to log out
        JButton btnLogout = new JButton("Log Out");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setBackground(new Color(255, 69, 0)); // Red-Orange color
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBounds(450, 10, 100, 30);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> {
            // Redirect to Login form
            new LoginForm().setVisible(true); // Open Login Form
            dispose(); // Close the dashboard
        });
        panel.add(btnLogout);

        // Show the dashboard
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboardForm();
    }
}
