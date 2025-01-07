/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import models.Room;
import models.Booking;
import Database.RoomDB;
import Database.BookingDB;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import models.User;

public class GuestDashboardForm extends JFrame {

    private User user;  // Store the User object
    private JComboBox<Room> roomComboBox;
    private JComboBox<String> paymentMethodComboBox;  // Added combo box for payment methods
    private JTextField transactionNumberField;  // Field for transaction number
    private JDateChooser checkInDateChooser;
    private JDateChooser checkOutDateChooser;
    private JButton btnBookRoom;
    private JButton viewRoom;

    // Constructor to accept a User object
    public GuestDashboardForm(User user) {
        this.user = user;  // Assign the User object
        setTitle("Guest Dashboard - Welcome to Our Guest House");
        setSize(600, 500);  // Increased size for additional fields
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255));  // Set background color to white for a clean look
        add(panel);

        // Title label
        JLabel lblTitle = new JLabel("Book a Room");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(200, 20, 250, 30);
        panel.add(lblTitle);

        // Room selection combo box
        JLabel lblSelectRoom = new JLabel("Select Room:");
        lblSelectRoom.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSelectRoom.setBounds(50, 80, 100, 30);
        panel.add(lblSelectRoom);

        roomComboBox = new JComboBox<>();
        roomComboBox.setBounds(200, 80, 250, 30);
        panel.add(roomComboBox);

        // Check-in date picker
        JLabel lblCheckIn = new JLabel("Check-in Date:");
        lblCheckIn.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCheckIn.setBounds(50, 130, 120, 30);
        panel.add(lblCheckIn);

        checkInDateChooser = new JDateChooser();
        checkInDateChooser.setBounds(200, 130, 250, 30);
        panel.add(checkInDateChooser);

        // Check-out date picker
        JLabel lblCheckOut = new JLabel("Check-out Date:");
        lblCheckOut.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCheckOut.setBounds(50, 180, 120, 30);
        panel.add(lblCheckOut);

        checkOutDateChooser = new JDateChooser();
        checkOutDateChooser.setBounds(200, 180, 250, 30);
        panel.add(checkOutDateChooser);

        // Payment method combo box
        JLabel lblPaymentMethod = new JLabel("Payment Method:");
        lblPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPaymentMethod.setBounds(50, 230, 150, 30);
        panel.add(lblPaymentMethod);

        paymentMethodComboBox = new JComboBox<>(new String[]{"Tele Birr", "CBE", "Abyssinia"});
        paymentMethodComboBox.setBounds(200, 230, 250, 30);
        panel.add(paymentMethodComboBox);

        // Transaction number label and field
        JLabel lblTransactionNumber = new JLabel("Transaction Number:");
        lblTransactionNumber.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTransactionNumber.setBounds(50, 280, 150, 30);
        panel.add(lblTransactionNumber);
        
        // "View Available Rooms" button with stylish look
        viewRoom = new JButton("View Available Rooms");
        viewRoom.setFont(new Font("Arial", Font.BOLD, 16));
        viewRoom.setBackground(new Color(30, 144, 255));  // Deep Sky Blue background
        viewRoom.setForeground(Color.WHITE);  // White text for contrast
        viewRoom.setBounds(50, 330, 200, 40);
        viewRoom.setFocusPainted(false);
        viewRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewRoom.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        // Hover effect for "View Available Rooms" button
        viewRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewRoom.setBackground(new Color(0, 191, 255));  // Lighter blue on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewRoom.setBackground(new Color(30, 144, 255));  // Original color when mouse exits
            }
        });
        viewRoom.addActionListener((ActionEvent e) -> loadAvailableRooms());
        panel.add(viewRoom);

        transactionNumberField = new JTextField();
        transactionNumberField.setBounds(200, 280, 250, 30);
        transactionNumberField.setVisible(false);  // Initially hide the transaction number field
        panel.add(transactionNumberField);

        // "Book Room" button with stylish look
        btnBookRoom = new JButton("Book Room");
        btnBookRoom.setFont(new Font("Arial", Font.BOLD, 16));
        btnBookRoom.setBackground(new Color(60, 179, 113));  // Medium Sea Green
        btnBookRoom.setForeground(Color.WHITE);
        btnBookRoom.setBounds(270, 330, 180, 40);  // Align horizontally with the first button
        btnBookRoom.setFocusPainted(false);
        btnBookRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBookRoom.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        btnBookRoom.addActionListener((ActionEvent e) -> bookRoom());
        panel.add(btnBookRoom);

        // Show the transaction number field only for specific payment methods
        paymentMethodComboBox.addActionListener(e -> {
            String selectedMethod = (String) paymentMethodComboBox.getSelectedItem();
            if (selectedMethod.equals("Tele Birr") || selectedMethod.equals("CBE") || selectedMethod.equals("Abyssinia")) {
                transactionNumberField.setVisible(true);  // Show the transaction number field
            } else {
                transactionNumberField.setVisible(false);  // Hide the transaction number field
            }
        });

        setVisible(true);
    }

    private void loadAvailableRooms() {
        try {
            // Get selected dates from the date pickers
            LocalDate checkInDate = getDateFromChooser(checkInDateChooser);
            LocalDate checkOutDate = getDateFromChooser(checkOutDateChooser);

            // Validate that both dates are selected
            if (checkInDate == null || checkOutDate == null) {
                JOptionPane.showMessageDialog(this, "Please select both check-in and check-out dates.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Ensure check-in date is before check-out date
            if (checkInDate.isAfter(checkOutDate)) {
                JOptionPane.showMessageDialog(this, "Check-out date cannot be before check-in date.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<Room> rooms = RoomDB.getAvailableRooms(checkInDate, checkOutDate);

            // Populate the combo box with the available rooms
            DefaultComboBoxModel<Room> model = new DefaultComboBoxModel<>();
            for (Room room : rooms) {
                model.addElement(room);
            }
            roomComboBox.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            // Display error message to the user
            JOptionPane.showMessageDialog(this, "Error loading available rooms!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bookRoom() {
        Room selectedRoom = (Room) roomComboBox.getSelectedItem();
        if (selectedRoom == null) {
            JOptionPane.showMessageDialog(this, "Please select a room.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate checkInDate = getDateFromChooser(checkInDateChooser);
        LocalDate checkOutDate = getDateFromChooser(checkOutDateChooser);

        if (checkInDate == null || checkOutDate == null) {
            JOptionPane.showMessageDialog(this, "Please select valid dates.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (checkInDate.isAfter(checkOutDate)) {
            JOptionPane.showMessageDialog(this, "Check-out date cannot be before check-in date.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate stay duration is at least 1 day
        long stayDuration = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (stayDuration < 1) {
            JOptionPane.showMessageDialog(this, "Stay duration must be at least 1 day.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String transactionNumber = null;

        // Check if payment method requires a transaction number
        if (paymentMethod.equals("Tele Birr") || paymentMethod.equals("CBE") || paymentMethod.equals("Abyssinia")) {
            transactionNumber = transactionNumberField.getText();
            if (transactionNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a transaction number.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Pass 0 for bookingId as it's autogenerated in the database
        Booking booking = new Booking(0, user.getId(), selectedRoom.getRoomId(), checkInDate, checkOutDate, "Pending", paymentMethod, transactionNumber);

        try {
            BookingDB.addBooking(booking);
            JOptionPane.showMessageDialog(this, "Room booked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            selectedRoom.setStatus("Booked");
            RoomDB.updateRoom(selectedRoom);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error booking room. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private LocalDate getDateFromChooser(JDateChooser dateChooser) {
        if (dateChooser.getDate() != null) {
            return LocalDate.ofInstant(dateChooser.getDate().toInstant(), java.time.ZoneId.systemDefault());
        }
        return null;
    }

    public static void main(String[] args) {
        new GuestDashboardForm(new User(1, "Test User", "testuser", "test@example.com", "password")); // Example user
    }
}

