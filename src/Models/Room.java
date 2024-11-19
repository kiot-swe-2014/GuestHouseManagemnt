/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class Room {

    private String roomNumber;   // Unique room number
    private String roomType;     // Type of the room (Single, Double, Suite, etc.)
    private boolean isAvailable; // Availability status (true if available)
    private double price;        // Price per night for the room
    private int capacity;        // Room capacity (e.g., 2 people, 4 people, etc.)

    // Constructor to initialize the room
    public Room(String roomNumber, String roomType, boolean isAvailable, double price, int capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.price = price;
        this.capacity = capacity;
    }

    // Getters and Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Method to mark the room as occupied
    public void occupyRoom() {
        this.isAvailable = false;
    }

    // Method to mark the room as available
    public void makeAvailable() {
        this.isAvailable = true;
    }

    // Method to display room details (could be used to show in the admin dashboard)
    public String getRoomDetails() {
        String availability = isAvailable ? "Available" : "Occupied";
        return "Room Number: " + roomNumber + "\n" +
               "Room Type: " + roomType + "\n" +
               "Price: $" + price + " per night\n" +
               "Capacity: " + capacity + " persons\n" +
               "Status: " + availability;
    }

    // Method to compare room prices
    public int comparePrice(Room otherRoom) {
        if (this.price < otherRoom.price) {
            return -1;  // This room is cheaper
        } else if (this.price > otherRoom.price) {
            return 1;   // This room is more expensive
        }
        return 0;   // Both rooms have the same price
    }

}
