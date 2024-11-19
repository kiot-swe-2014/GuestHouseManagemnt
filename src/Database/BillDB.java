package Database;

import models.Bill;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/guesthouse"; // Change to your DB URL
    private static final String DB_USER = "root";  // Change to your MySQL username
    private static final String DB_PASSWORD = ""; // Change to your MySQL password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Create a new bill record in the database
    public boolean createBill(Bill bill) {
        String sql = "INSERT INTO bills (guest_name, guest_phone, room_type, room_number, check_in_date, check_out_date, amount, payment_status, bill_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bill.getGuestName());
            stmt.setString(2, bill.getGuestPhone());
            stmt.setString(3, bill.getRoomType());
            stmt.setString(4, bill.getRoomNumber());
            stmt.setDate(5, java.sql.Date.valueOf(bill.getCheckInDate())); // Convert LocalDate to java.sql.Date
            stmt.setDate(6, java.sql.Date.valueOf(bill.getCheckOutDate())); // Convert LocalDate to java.sql.Date
            stmt.setDouble(7, bill.getTotalAmount());
            stmt.setString(8, "Unpaid"); // Assuming the payment status is "Unpaid" by default
            stmt.setDate(9, java.sql.Date.valueOf(bill.getBillDate())); // Convert LocalDate to java.sql.Date

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Return true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a bill by reservation ID
    public Bill getBillByReservationId(int reservationId) {
        String sql = "SELECT * FROM bills WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Handle null for bill_date and safely convert it to LocalDate
                LocalDate billDate = null;
                Date sqlDate = rs.getDate("bill_date");
                if (sqlDate != null) {
                    billDate = sqlDate.toLocalDate();
                }

                return new Bill(
                        rs.getString("guest_name"),
                        rs.getString("guest_phone"),
                        rs.getString("room_type"),
                        rs.getString("room_number"),
                        rs.getDate("check_in_date").toLocalDate(),  // Convert java.sql.Date to LocalDate
                        rs.getDate("check_out_date").toLocalDate(), // Convert java.sql.Date to LocalDate
                        billDate  // Use LocalDate for billDate
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no bill found for the given reservation ID
    }

    // Retrieve all bills from the database
    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Handle null for bill_date and safely convert it to LocalDate
                LocalDate billDate = null;
                Date sqlDate = rs.getDate("bill_date");
                if (sqlDate != null) {
                    billDate = sqlDate.toLocalDate();
                }

                bills.add(new Bill(
                        rs.getString("guest_name"),
                        rs.getString("guest_phone"),
                        rs.getString("room_type"),
                        rs.getString("room_number"),
                        rs.getDate("check_in_date").toLocalDate(),  // Convert java.sql.Date to LocalDate
                        rs.getDate("check_out_date").toLocalDate(), // Convert java.sql.Date to LocalDate
                        billDate // Adding the bill date (can be null)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    // Update the payment status of a bill
    public boolean updatePaymentStatus(int reservationId, String paymentStatus) {
        String sql = "UPDATE bills SET payment_status = ? WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paymentStatus);
            stmt.setInt(2, reservationId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a bill by reservation ID
    public boolean deleteBill(int reservationId) {
        String sql = "DELETE FROM bills WHERE reservation_id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
