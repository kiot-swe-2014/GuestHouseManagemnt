/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationManagementForm extends JFrame {

    private JTextField txtReservationID, txtGuestName;
    private JButton btnViewReservation, btnCancelReservation, btnBack;

    public ReservationManagementForm() {
        // Setup the frame
        setTitle("Reservation Management - Guest House");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        setResizable(false);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));  // Light Gray Background
        add(panel);

        // Title
        JLabel lblTitle = new JLabel("Manage Reservations");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(150, 20, 300, 30);
        panel.add(lblTitle);

        // Reservation ID Label and TextField
        JLabel lblReservationID = new JLabel("Reservation ID:");
        lblReservationID.setBounds(50, 100, 120, 25);
        panel.add(lblReservationID);

        txtReservationID = new JTextField();
        txtReservationID.setBounds(180, 100, 200, 25);
        panel.add(txtReservationID);

        // Guest Name Label and TextField
        JLabel lblGuestName = new JLabel("Guest Name:");
        lblGuestName.setBounds(50, 150, 120, 25);
        panel.add(lblGuestName);

        txtGuestName = new JTextField();
        txtGuestName.setBounds(180, 150, 200, 25);
        panel.add(txtGuestName);

        // View Reservation Button
        btnViewReservation = new JButton("View Reservation");
        btnViewReservation.setBounds(50, 200, 150, 40);
        btnViewReservation.setBackground(new Color(34, 193, 195));
        btnViewReservation.setForeground(Color.WHITE);
        btnViewReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle viewing a reservation (Currently just a placeholder)
                String reservationID = txtReservationID.getText();
                String guestName = txtGuestName.getText();
                JOptionPane.showMessageDialog(null, "Reservation Details:\nID: " + reservationID + "\nGuest: " + guestName);
            }
        });
        panel.add(btnViewReservation);

        // Cancel Reservation Button
        btnCancelReservation = new JButton("Cancel Reservation");
        btnCancelReservation.setBounds(250, 200, 150, 40);
        btnCancelReservation.setBackground(new Color(255, 99, 71));
        btnCancelReservation.setForeground(Color.WHITE);
        btnCancelReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle canceling a reservation (Currently just a placeholder)
                String reservationID = txtReservationID.getText();
                JOptionPane.showMessageDialog(null, "Reservation ID " + reservationID + " canceled.");
            }
        });
        panel.add(btnCancelReservation);

        // Back Button (Returns to Admin Dashboard)
        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(200, 300, 200, 40);
        btnBack.setBackground(new Color(60, 179, 113));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminDashboardForm();  // Return to Dashboard
                dispose();  // Close Reservation Management form
            }
        });
        panel.add(btnBack);

        // Display the form
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReservationManagementForm();
    }
}
