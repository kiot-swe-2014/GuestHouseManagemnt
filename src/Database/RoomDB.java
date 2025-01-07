/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import models.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class RoomDB {

    // Fetch all rooms from the database
    public static ArrayList<Room> getAllRooms() throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM rooms")) {
            
            while (rs.next()) {
                Room room = new Room(
                    rs.getInt("room_id"),
                    rs.getString("type"),
                    rs.getString("status"),
                    rs.getDouble("price")
                );
                rooms.add(room);
            }
        }
        return rooms;
    }

    // Add a new room to the database
    public static void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO rooms (type, status, price) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, room.getType());
            pstmt.setString(2, room.getStatus());
            pstmt.setDouble(3, room.getPrice());
            pstmt.executeUpdate();
        }
    }

    // Update room details in the database
    public static void updateRoom(Room room) throws SQLException {
        String query = "UPDATE rooms SET type = ?, status = ?, price = ? WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, room.getType());
            pstmt.setString(2, room.getStatus());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setInt(4, room.getRoomId());
            pstmt.executeUpdate();
        }
    }

    // Delete a room from the database
    public static void deleteRoom(int roomId) throws SQLException {
        String query = "DELETE FROM rooms WHERE room_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();
        }
    }

    // Fetch rooms available for specific dates
    public static ArrayList<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate) throws SQLException {
        ArrayList<Room> availableRooms = new ArrayList<>();
        
          if (checkInDate == null || checkOutDate == null) {
        throw new IllegalArgumentException("Check-in and Check-out dates cannot be null.");
    }
          
        String query = "SELECT * FROM rooms WHERE status = 'Available' AND room_id NOT IN " +
                "(SELECT room_id FROM bookings WHERE (check_in_date <= ? AND check_out_date >= ?))";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setDate(1, Date.valueOf(checkOutDate));
            pstmt.setDate(2, Date.valueOf(checkInDate));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room(
                        rs.getInt("room_id"),
                        rs.getString("type"),
                        rs.getString("status"),
                        rs.getDouble("price")
                    );
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }
}
