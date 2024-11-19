package gui;

import com.toedter.calendar.JDateChooser;  // Import for JDateChooser
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationForm extends JFrame {

    private JTextField txtGuestName, txtGuestPhone;
    private JComboBox<String> cmbRoomNumber, cmbRoomType;
    private JDateChooser checkInDate, checkOutDate;
    private JButton btnReserve, btnBack;
    private JLabel lblErrorMessage;

    public ReservationForm() {
        // Setup the frame
        setTitle("Reservation - Guest House");
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
        JLabel lblTitle = new JLabel("Make a Reservation");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(180, 20, 250, 30);
        panel.add(lblTitle);

        // Guest Name Label and TextField
        JLabel lblGuestName = new JLabel("Guest Name:");
        lblGuestName.setBounds(50, 80, 120, 25);
        panel.add(lblGuestName);

        txtGuestName = new JTextField();
        txtGuestName.setBounds(180, 80, 200, 25);
        panel.add(txtGuestName);

        // Guest Phone Label and TextField
        JLabel lblGuestPhone = new JLabel("Guest Phone:");
        lblGuestPhone.setBounds(50, 120, 120, 25);
        panel.add(lblGuestPhone);

        txtGuestPhone = new JTextField();
        txtGuestPhone.setBounds(180, 120, 200, 25);
        panel.add(txtGuestPhone);

        // Room Type Label and ComboBox
        JLabel lblRoomType = new JLabel("Room Type:");
        lblRoomType.setBounds(50, 160, 120, 25);
        panel.add(lblRoomType);

        cmbRoomType = new JComboBox<>(new String[] { "Single", "Double", "Suite" });
        cmbRoomType.setBounds(180, 160, 200, 25);
        panel.add(cmbRoomType);

        // Room Number Label and ComboBox
        JLabel lblRoomNumber = new JLabel("Room Number:");
        lblRoomNumber.setBounds(50, 200, 120, 25);
        panel.add(lblRoomNumber);

        cmbRoomNumber = new JComboBox<>(new String[] { "101", "102", "103", "104", "105" });
        cmbRoomNumber.setBounds(180, 200, 200, 25);
        panel.add(cmbRoomNumber);

        // Check-In Date Label and DateChooser
        JLabel lblCheckIn = new JLabel("Check-In Date:");
        lblCheckIn.setBounds(50, 240, 120, 25);
        panel.add(lblCheckIn);

        checkInDate = new JDateChooser();
        checkInDate.setBounds(180, 240, 200, 25);
        panel.add(checkInDate);

        // Check-Out Date Label and DateChooser
        JLabel lblCheckOut = new JLabel("Check-Out Date:");
        lblCheckOut.setBounds(50, 280, 120, 25);
        panel.add(lblCheckOut);

        checkOutDate = new JDateChooser();
        checkOutDate.setBounds(180, 280, 200, 25);
        panel.add(checkOutDate);

        // Error message label (initially hidden)
        lblErrorMessage = new JLabel();
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setBounds(50, 310, 300, 25);
        lblErrorMessage.setVisible(false);
        panel.add(lblErrorMessage);

        // Reserve Button
        btnReserve = new JButton("Reserve Room");
        btnReserve.setBounds(50, 340, 150, 40);
        btnReserve.setBackground(new Color(34, 193, 195));
        btnReserve.setForeground(Color.WHITE);
        btnReserve.setFocusPainted(false);
        btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate and process reservation
                String guestName = txtGuestName.getText();
                String guestPhone = txtGuestPhone.getText();
                String roomNumber = (String) cmbRoomNumber.getSelectedItem();
                String roomType = (String) cmbRoomType.getSelectedItem();

                Date checkIn = checkInDate.getDate();
                Date checkOut = checkOutDate.getDate();

                // Basic validation
                if (guestName.isEmpty() || guestPhone.isEmpty() || checkIn == null || checkOut == null) {
                    lblErrorMessage.setText("Please fill all fields.");
                    lblErrorMessage.setVisible(true);
                    return;
                }

                if (checkIn.after(checkOut)) {
                    lblErrorMessage.setText("Check-Out date cannot be before Check-In date.");
                    lblErrorMessage.setVisible(true);
                    return;
                }

                // Format the dates
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String checkInStr = sdf.format(checkIn);
                String checkOutStr = sdf.format(checkOut);

                // Successful reservation message (Placeholder for actual reservation logic)
                JOptionPane.showMessageDialog(null, "Reservation Successful!\n" +
                        "Guest Name: " + guestName + "\n" +
                        "Phone: " + guestPhone + "\n" +
                        "Room Type: " + roomType + "\n" +
                        "Room Number: " + roomNumber + "\n" +
                        "Check-In: " + checkInStr + "\n" +
                        "Check-Out: " + checkOutStr);
            }
        });
        panel.add(btnReserve);

        // Back Button (Return to Admin Dashboard or previous page)
        btnBack = new JButton("Back");
        btnBack.setBounds(220, 340, 150, 40);
        btnBack.setBackground(new Color(60, 179, 113));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close Reservation Form and return to Admin Dashboard (or previous form)
                new AdminDashboardForm();  // Assuming Admin Dashboard as a placeholder
                dispose();  // Close current form
            }
        });
        panel.add(btnBack);

        // Display the form
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReservationForm();
    }
}
