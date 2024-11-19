/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import models.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/guesthouse";  // Change to your DB URL
    private static final String DB_USER = "root";  // Change to your MySQL username
    private static final String DB_PASSWORD = "";  // Change to your MySQL password

    // Establish a connection to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Create a new room in the database
    public boolean createRoom(Room room) {
        String sql = "INSERT INTO rooms (room_number, room_type, price, status, capacity) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, room.getRoomNumber());
            stmt.setString(2, room.getRoomType());
            stmt.setDouble(3, room.getPrice());
            stmt.setString(4, room.isAvailable() ? "Available" : "Occupied"); // Map boolean to string
            stmt.setInt(5, room.getCapacity());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a room by room ID
    public Room getRoomById(int roomId) {
        String sql = "SELECT * FROM rooms WHERE room_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Room(
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getString("status").equals("Available"), // Convert string to boolean
                        rs.getDouble("price"),
                        rs.getInt("capacity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no room found for the given roomId
    }

    // Get all rooms from the database
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getString("status").equals("Available"), // Convert string to boolean
                        rs.getDouble("price"),
                        rs.getInt("capacity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Update the status of a room (e.g., Available, Occupied, Under Maintenance)
    public boolean updateRoomStatus(int roomId, boolean isAvailable) {
        String sql = "UPDATE rooms SET status = ? WHERE room_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isAvailable ? "Available" : "Occupied"); // Convert boolean to string
            stmt.setInt(2, roomId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a room by room ID
    public boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM rooms WHERE room_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, roomId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
