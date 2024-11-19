/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

public class User {

    private int userId;       // Primary Key
    private String username;  // Username
    private String password;  // Password
    private String fullName;  // Full name of the user
    private String status;    // User status (active or inactive)

    // Constructor for the user
    public User(int userId, String username, String password, String fullName, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.status = status;
    }

    // Getters and setters for each field
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Optional: You can add custom methods for handling user logic like 'isActive()' if needed
    public boolean isActive() {
        return "active".equalsIgnoreCase(status); // Checks if the user is active
    }
}
