/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private int userId;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private String paymentMethod;  // New field for payment method
    private String transactionNumber;  // New field for transaction number

    // Updated constructor to include payment method and transaction number
    public Booking(int bookingId, int userId, int roomId, LocalDate checkInDate, LocalDate checkOutDate, 
                   String status, String paymentMethod, String transactionNumber) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.transactionNumber = transactionNumber;
    }

    // Getters and setters
    public int getBookingId() { 
        return bookingId; 
    }
    public void setBookingId(int bookingId) { 
        this.bookingId = bookingId; 
    }

    public int getUserId() { 
        return userId; 
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }

    public int getRoomId() { 
        return roomId; 
    }
    public void setRoomId(int roomId) { 
        this.roomId = roomId; 
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

    public String getStatus() { 
        return status; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getPaymentMethod() { 
        return paymentMethod; 
    }
    public void setPaymentMethod(String paymentMethod) { 
        this.paymentMethod = paymentMethod; 
    }

    public String getTransactionNumber() { 
        return transactionNumber; 
    }
    public void setTransactionNumber(String transactionNumber) { 
        this.transactionNumber = transactionNumber; 
    }
}
