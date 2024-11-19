/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportsForm extends JFrame {

    private JButton btnGenerateReport, btnBack;
    private JTextArea txtReportArea;

    public ReportsForm() {
        // Setup the frame
        setTitle("Reports - Guest House");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
        setResizable(false);

        // Panel for the form
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));  // Light Gray Background
        add(panel);

        // Title
        JLabel lblTitle = new JLabel("Generate Reports");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBounds(200, 20, 250, 30);
        panel.add(lblTitle);

        // Text Area for displaying reports
        txtReportArea = new JTextArea();
        txtReportArea.setEditable(false);
        txtReportArea.setBounds(50, 100, 500, 150);
        txtReportArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(txtReportArea);

        // Button to generate the report
        btnGenerateReport = new JButton("Generate Report");
        btnGenerateReport.setBounds(50, 270, 200, 40);
        btnGenerateReport.setBackground(new Color(34, 193, 195));
        btnGenerateReport.setForeground(Color.WHITE);
        btnGenerateReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate a placeholder report (Can be replaced by actual data fetching)
                String report = "Occupancy Rate: 80%\nRevenue: $5000\nTotal Reservations: 120";
                txtReportArea.setText(report);
            }
        });
        panel.add(btnGenerateReport);

        // Back Button (Returns to Admin Dashboard)
        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(250, 270, 200, 40);
        btnBack.setBackground(new Color(60, 179, 113));
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminDashboardForm();  // Return to Dashboard
                dispose();  // Close Report Generation form
            }
        });
        panel.add(btnBack);

        // Display the form
        setVisible(true);
    }

    public static void main(String[] args) {
        new ReportsForm();
    }
}

