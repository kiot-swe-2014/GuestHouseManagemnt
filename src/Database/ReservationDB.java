/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;



import models.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class ReservationDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/guesthouse";  // Change to your DB URL
    private static final String DB_USER = "root";  // Change to your MySQL username
    private static final String DB_PASSWORD = "";  // Change to your MySQL password

    // Establish a connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Create a new reservation
    public boolean createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (guest_name, room_id, check_in_date, check_out_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getGuestName());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            stmt.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            stmt.setString(5, reservation.getStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a reservation by its ID
    public Reservation getReservationById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no reservation found for the given reservationId
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Update the status of a reservation (e.g., "Confirmed", "Cancelled")
    public boolean updateReservationStatus(int reservationId, String status) {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, reservationId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a reservation by reservation ID
    public boolean deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all reservations by room ID (optional feature)
    public List<Reservation> getReservationsByRoomId(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE room_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in_date").toLocalDate(),
                        rs.getDate("check_out_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}
