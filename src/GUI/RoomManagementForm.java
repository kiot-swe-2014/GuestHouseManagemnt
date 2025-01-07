/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import Database.RoomDB;
import models.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class RoomManagementForm extends JFrame {

    private JTextField txtRoomNumber;
    private JTextField txtRoomType;
    private JTextField txtRoomPrice;
    private JComboBox<String> cmbRoomStatus;
    private JButton btnAddRoom, btnDeleteRoom, btnUpdateRoom, btnBack;

    public RoomManagementForm() {
        // Setup the frame
        setTitle("Room Management - Guest House");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); // Light Gray Background
        add(panel);

        // Title
        JLabel lblTitle = new JLabel("Manage Rooms");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(200, 20, 250, 30);
        panel.add(lblTitle);

        // Room ID Label and TextField
        JLabel lblRoomNumber = new JLabel("Room ID:");
        lblRoomNumber.setBounds(50, 80, 120, 25);
        panel.add(lblRoomNumber);

        txtRoomNumber = new JTextField();
        txtRoomNumber.setBounds(180, 80, 200, 25);
        panel.add(txtRoomNumber);

        // Room Type Label and TextField
        JLabel lblRoomType = new JLabel("Room Type:");
        lblRoomType.setBounds(50, 130, 120, 25);
        panel.add(lblRoomType);

        txtRoomType = new JTextField();
        txtRoomType.setBounds(180, 130, 200, 25);
        panel.add(txtRoomType);

        // Room Price Label and TextField
        JLabel lblRoomPrice = new JLabel("Room Price:");
        lblRoomPrice.setBounds(50, 180, 120, 25);
        panel.add(lblRoomPrice);

        txtRoomPrice = new JTextField();
        txtRoomPrice.setBounds(180, 180, 200, 25);
        panel.add(txtRoomPrice);

        // Room Status Label and ComboBox
        JLabel lblRoomStatus = new JLabel("Room Status:");
        lblRoomStatus.setBounds(50, 230, 120, 25);
        panel.add(lblRoomStatus);

        cmbRoomStatus = new JComboBox<>(new String[]{"Available", "Occupied", "Maintenance"});
        cmbRoomStatus.setBounds(180, 230, 200, 25);
        panel.add(cmbRoomStatus);

        // Add Room Button
        btnAddRoom = new JButton("Add Room");
        btnAddRoom.setBounds(50, 300, 150, 40);
        btnAddRoom.setBackground(new Color(34, 193, 195));
        btnAddRoom.setForeground(Color.WHITE);
        btnAddRoom.addActionListener(this::handleAddRoom);
        panel.add(btnAddRoom);

        // Update Room Button
        btnUpdateRoom = new JButton("Update Room");
        btnUpdateRoom.setBounds(220, 300, 150, 40);
        btnUpdateRoom.setBackground(new Color(255, 165, 0));
        btnUpdateRoom.setForeground(Color.WHITE);
        btnUpdateRoom.addActionListener(this::handleUpdateRoom);
        panel.add(btnUpdateRoom);

        // Delete Room Button
        btnDeleteRoom = new JButton("Delete Room");
        btnDeleteRoom.setBounds(390, 300, 150, 40);
        btnDeleteRoom.setBackground(new Color(255, 99, 71));
        btnDeleteRoom.setForeground(Color.WHITE);
        btnDeleteRoom.addActionListener(this::handleDeleteRoom);
        panel.add(btnDeleteRoom);

        // Back Button (Returns to Admin Dashboard)
        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(200, 400, 200, 40);
        btnBack.setBackground(new Color(60, 179, 113));
        btnBack.setForeground(Color.WHITE);
       // Back Button (Returns to Admin Dashboard)
btnBack.addActionListener(e -> {
    AdminDashboardForm adminDashboardForm = new AdminDashboardForm();
    adminDashboardForm.setVisible(true);  // Ensure the dashboard form is visible
    dispose();  // Close the Room Management form
});

        panel.add(btnBack);

        // Display the form
        setVisible(true);
    }

    // Handle Add Room
    private void handleAddRoom(ActionEvent e) {
        try {
            String type = txtRoomType.getText();
            String status = cmbRoomStatus.getSelectedItem().toString();
            double price = Double.parseDouble(txtRoomPrice.getText());

            Room room = new Room(0, type, status, price); // 0 for new room ID
            RoomDB.addRoom(room);
            JOptionPane.showMessageDialog(this, "Room added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding room. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle Update Room
    private void handleUpdateRoom(ActionEvent e) {
        try {
            int roomId = Integer.parseInt(txtRoomNumber.getText());
            String type = txtRoomType.getText();
            String status = cmbRoomStatus.getSelectedItem().toString();
            double price = Double.parseDouble(txtRoomPrice.getText());

            Room room = new Room(roomId, type, status, price);
            RoomDB.updateRoom(room);
            JOptionPane.showMessageDialog(this, "Room updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating room. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid Room ID and price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle Delete Room
    private void handleDeleteRoom(ActionEvent e) {
        try {
            int roomId = Integer.parseInt(txtRoomNumber.getText());
            RoomDB.deleteRoom(roomId);
            JOptionPane.showMessageDialog(this, "Room deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting room. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Room ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomManagementForm::new);
    }
}

