/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomManagementForm extends JFrame {

    private JTextField txtRoomNumber;
    private JComboBox<String> cmbRoomStatus;
    private JButton btnAddRoom, btnDeleteRoom, btnBack;

    public RoomManagementForm() {
        // Setup the frame
        setTitle("Room Management - Guest House");
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
        JLabel lblTitle = new JLabel("Manage Rooms");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(200, 20, 250, 30);
        panel.add(lblTitle);

        // Room Number Label and TextField
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(50, 100, 120, 25);
        panel.add(lblRoomNumber);

        txtRoomNumber = new JTextField();
        txtRoomNumber.setBounds(180, 100, 200, 25);
        panel.add(txtRoomNumber);

        // Room Status Label and ComboBox
        JLabel lblRoomStatus = new JLabel("Room Status:");
        lblRoomStatus.setBounds(50, 150, 120, 25);
        panel.add(lblRoomStatus);

        cmbRoomStatus = new JComboBox<>(new String[] { "Available", "Occupied", "Maintenance" });
        cmbRoomStatus.setBounds(180, 150, 200, 25);
        panel.add(cmbRoomStatus);

        // Add Room Button
        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBounds(50, 200, 150, 40);
        btnAddRoom.setBackground(new Color(34, 193, 195));
        btnAddRoom.setForeground(Color.WHITE);
        btnAddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle adding a new room (Currently just a placeholder)
                String roomNumber = txtRoomNumber.getText();
                String roomStatus = cmbRoomStatus.getSelectedItem().toString();
                JOptionPane.showMessageDialog(null, "Room " + roomNumber + " added with status " + roomStatus);
            }
        });
        panel.add(btnAddRoom);

        // Delete Room Button
        btnDeleteRoom = new JButton("Delete Room");
        btnDeleteRoom.setBounds(250, 200, 150, 40);
        btnDeleteRoom.setBackground(new Color(255, 99, 71));
        btnDeleteRoom.setForeground(Color.WHITE);
        btnDeleteRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle room deletion (Currently just a placeholder)
                String roomNumber = txtRoomNumber.getText();
                JOptionPane.showMessageDialog(null, "Room " + roomNumber + " deleted.");
            }
        });
        panel.add(btnDeleteRoom);

        // Back Button (Returns to Admin Dashboard)
        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(200, 300, 200, 40);
        btnBack.setBackground(new Color(60, 179, 113));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminDashboardForm();  // Return to Dashboard
                dispose();  // Close Room Management form
            }
        });
        panel.add(btnBack);

        // Display the form
        setVisible(true);
    }

    public static void main(String[] args) {
        new RoomManagementForm();
    }
}
