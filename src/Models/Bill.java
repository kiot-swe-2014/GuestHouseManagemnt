package models;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Bill {

    // Attributes for the bill
    private String guestName;
    private String guestPhone;
    private String roomType;
    private String roomNumber;
    private LocalDate checkInDate;  // Changed to LocalDate
    private LocalDate checkOutDate; // Changed to LocalDate
    private double roomPrice;
    private double totalAmount;
    private LocalDate billDate;

    // Constructor
    public Bill(String guestName, String guestPhone, String roomType, String roomNumber, 
                LocalDate checkInDate, LocalDate checkOutDate, LocalDate billDate) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.billDate = billDate;

        // Set room price based on room type
        setRoomPrice(roomType);

        // Calculate the total amount when the bill is created
        calculateBill();
    }

    // Method to set room price based on room type
    private void setRoomPrice(String roomType) {
        switch (roomType.toLowerCase()) {
            case "single":
                this.roomPrice = 100.0;  // Example price for a single room
                break;
            case "double":
                this.roomPrice = 150.0;  // Example price for a double room
                break;
            case "suite":
                this.roomPrice = 200.0;  // Example price for a suite
                break;
            default:
                this.roomPrice = 100.0;  // Default price if room type is not recognized
                break;
        }
    }

    // Method to calculate the total bill
    public void calculateBill() {
        long daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate); // Calculate duration in days

        // Calculate total amount
        this.totalAmount = daysStayed * roomPrice;
    }

    // Method to generate the bill (Invoice format)
    public String generateInvoice() {
        // Format the total amount to two decimal places
        DecimalFormat df = new DecimalFormat("#.00");
        String totalAmountFormatted = df.format(totalAmount);

        // Generate invoice details
        StringBuilder invoice = new StringBuilder();
        invoice.append("======== INVOICE ========\n")
                .append("Guest Name: ").append(guestName).append("\n")
                .append("Phone: ").append(guestPhone).append("\n")
                .append("Room Type: ").append(roomType).append("\n")
                .append("Room Number: ").append(roomNumber).append("\n")
                .append("Check-In Date: ").append(checkInDate).append("\n")
                .append("Check-Out Date: ").append(checkOutDate).append("\n")
                .append("Duration: ").append(calculateDuration()).append(" days\n")
                .append("Room Price per Night: $").append(roomPrice).append("\n")
                .append("Total Amount: $").append(totalAmountFormatted).append("\n")
                .append("=========================\n");

        return invoice.toString();
    }

    // Method to calculate the number of days between check-in and check-out dates
    private long calculateDuration() {
        return ChronoUnit.DAYS.between(checkInDate, checkOutDate);  // Calculate duration in days
    }

    // Getters and setters for other fields (if needed)

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
        setRoomPrice(roomType);  // Update price when room type is changed
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}
