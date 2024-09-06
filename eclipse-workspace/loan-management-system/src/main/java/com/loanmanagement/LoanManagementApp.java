package com.loanmanagement;

import java.sql.SQLException;

import com.loanmanagement.dao.DatabaseConnection;
import com.loanmanagement.ui.LoanManagementUI;

public class LoanManagementApp {
    public static void main(String[] args) throws SQLException {
        try {
            // Initialize database connection
            DatabaseConnection.getConnection();

            // Create and start the UI
            LoanManagementUI ui = new LoanManagementUI();
            ui.start();
        } catch (Exception e) {
            System.out.println("An error occurred while starting the application: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.getConnection();
        }
    }
}