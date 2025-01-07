/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Room {
    private int roomId;
    private String type;
    private String status;
    private double price;

    // Constructor
    public Room(int roomId, String type, String status, double price) {
        this.roomId = roomId;
        this.type = type;
        this.status = status;
        this.price = price;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override toString method for display in combo box
    @Override
    public String toString() {
        return type + " - $" + price + " (" + status + ")";
    }
}

