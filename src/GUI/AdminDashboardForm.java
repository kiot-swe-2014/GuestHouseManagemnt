/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardForm extends JFrame {

    private JButton btnManageRooms;
    private JButton btnManageReservations;
    private JButton btnGenerateReports;
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
        btnManageRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Room Management Form
                new RoomManagementForm(); 
                dispose(); // Close the dashboard
            }
        });
        panel.add(btnManageRooms);

        // Button to manage reservations
        btnManageReservations = new JButton("Manage Reservations");
        btnManageReservations.setFont(new Font("Arial", Font.BOLD, 16));
        btnManageReservations.setBackground(new Color(255, 99, 71)); // Tomato color
        btnManageReservations.setForeground(Color.WHITE);
        btnManageReservations.setBounds(200, 180, 200, 40);
        btnManageReservations.setFocusPainted(false);
        btnManageReservations.setBorderPainted(false);
        btnManageReservations.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnManageReservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Reservation Management Form
                new ReservationManagementForm(); 
                dispose(); // Close the dashboard
            }
        });
        panel.add(btnManageReservations);

        // Button to generate reports
        btnGenerateReports = new JButton("Generate Reports");
        btnGenerateReports.setFont(new Font("Arial", Font.BOLD, 16));
        btnGenerateReports.setBackground(new Color(60, 179, 113)); // Medium Sea Green
        btnGenerateReports.setForeground(Color.WHITE);
        btnGenerateReports.setBounds(200, 240, 200, 40);
        btnGenerateReports.setFocusPainted(false);
        btnGenerateReports.setBorderPainted(false);
        btnGenerateReports.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGenerateReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Reports Form
                new ReportsForm(); 
                dispose(); // Close the dashboard
            }
        });
        panel.add(btnGenerateReports);

        // Button to log out (Back to login screen)
        JButton btnLogout = new JButton("Log Out");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogout.setBackground(new Color(255, 69, 0)); // Red-Orange color
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setBounds(450, 10, 100, 30);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to Login form
                new LoginForm();
                dispose(); // Close the dashboard
            }
        });
        panel.add(btnLogout);

        // Show the dashboard
        setVisible(true);
    }

    public static void main(String[] args) {
        new AdminDashboardForm();
    }
}
