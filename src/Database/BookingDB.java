/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import models.Booking;
import java.sql.*;
import java.util.ArrayList;

public class BookingDB {

    // Add a new booking with payment method and transaction number
    public static boolean addBooking(Booking booking) throws SQLException {
        // First, check if the user_id exists in the users table
        if (!doesUserExist(booking.getUserId())) {
            throw new SQLException("User with ID " + booking.getUserId() + " does not exist.");
        }

        String query = "INSERT INTO bookings (user_id, room_id, check_in_date, check_out_date, status, payment_method, transaction_number) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRoomId());
            pstmt.setDate(3, Date.valueOf(booking.getCheckInDate()));
            pstmt.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            pstmt.setString(5, booking.getStatus());
            pstmt.setString(6, booking.getPaymentMethod());  // Set payment method
            pstmt.setString(7, booking.getTransactionNumber());  // Set transaction number
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Helper method to check if the user exists in the users table
    private static boolean doesUserExist(int userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false; // Return false if the user does not exist
    }

    // Get all bookings by user (for viewing past bookings or current bookings)
    public static ArrayList<Booking> getBookingsByUserId(int userId) throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("user_id"),
                    rs.getInt("room_id"),
                    rs.getDate("check_in_date").toLocalDate(),
                    rs.getDate("check_out_date").toLocalDate(),
                    rs.getString("status"),
                    rs.getString("payment_method"),  // Get payment method from ResultSet
                    rs.getString("transaction_number")  // Get transaction number from ResultSet
                );
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // Update booking status
    public static boolean updateBookingStatus(int bookingId, String status) throws SQLException {
        String query = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
