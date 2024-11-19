/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

public class Reservation {

    // Attributes for reservation
    private String reservationId;
    private String guestName;
    private String guestPhone;
    private String roomType;
    private String roomNumber;
    private Date checkInDate;
    private Date checkOutDate;
    private boolean isActive;
    private String paymentStatus;  // New field for tracking payment status

    // Constructor
    public Reservation(String reservationId, String guestName, String guestPhone, String roomType, 
                       String roomNumber, Date checkInDate, Date checkOutDate) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isActive = true;  // By default, a reservation is active when created
        this.paymentStatus = "Pending";  // Default payment status is Pending
    }

    // Getters and Setters for the reservation attributes
    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

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
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Getter and Setter for Payment Status
    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Method to calculate the number of days between check-in and check-out dates
    public int calculateDuration() {
        long checkInMillis = checkInDate.getTime();
        long checkOutMillis = checkOutDate.getTime();
        long durationMillis = checkOutMillis - checkInMillis;
        return (int) (durationMillis / (1000 * 60 * 60 * 24));  // Return duration in days
    }

    // Method to generate reservation details (could be used to print or display in a GUI)
    public String generateReservationSummary() {
        String summary = "Reservation ID: " + reservationId + "\n" +
                "Guest Name: " + guestName + "\n" +
                "Phone: " + guestPhone + "\n" +
                "Room Type: " + roomType + "\n" +
                "Room Number: " + roomNumber + "\n" +
                "Check-In Date: " + checkInDate.toString() + "\n" +
                "Check-Out Date: " + checkOutDate.toString() + "\n" +
                "Duration: " + calculateDuration() + " days\n" +
                "Reservation Status: " + (isActive ? "Active" : "Inactive") + "\n" +
                "Payment Status: " + paymentStatus + "\n";  // Include payment status in the summary

        return summary;
    }

    // Method to cancel a reservation (deactivate it)
    public void cancelReservation() {
        this.isActive = false;  // Set reservation status to inactive (cancelled)
    }

    // Method to reactivate a reservation
    public void reactivateReservation() {
        this.isActive = true;  // Set reservation status to active
    }

    // Method to update the payment status (e.g., mark as Paid, Pending, or Cancelled)
    public void updatePaymentStatus(String newStatus) {
        if (newStatus.equalsIgnoreCase("Paid") || newStatus.equalsIgnoreCase("Pending") || newStatus.equalsIgnoreCase("Cancelled")) {
            this.paymentStatus = newStatus;
        } else {
            System.out.println("Invalid payment status.");
        }
    }
}
