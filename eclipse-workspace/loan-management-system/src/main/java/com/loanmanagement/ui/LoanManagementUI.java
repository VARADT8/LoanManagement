package com.loanmanagement.ui;

import com.loanmanagement.dao.*;
import com.loanmanagement.model.Loan;
import com.loanmanagement.model.Payment;
import com.loanmanagement.service.LoanService;
import com.loanmanagement.service.PaymentService;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoanManagementUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final LoanService loanService;
    private final PaymentService paymentService;

    public LoanManagementUI() throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        LoanDAO loanDAO = new LoanDAOImpl(connection);
        PaymentDAO paymentDAO = new PaymentDAOImpl(connection);
        this.loanService = new LoanService(loanDAO);
        this.paymentService = new PaymentService(paymentDAO);
    }

    public void start() {
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    handleLoanManagement();
                    break;
                case 2:
                    handlePaymentManagement();
                    break;
                case 3:
                    System.out.println("Thank you for using the Loan Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n--- Loan Management System ---");
        System.out.println("1. Loan Management");
        System.out.println("2. Payment Management");
        System.out.println("3. Exit");
    }

    private void handleLoanManagement() {
        while (true) {
            displayLoanMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    addNewLoan();
                    break;
                case 2:
                    viewLoanDetails();
                    break;
                case 3:
                    updateLoanInformation();
                    break;
                case 4:
                    removeLoanAccount();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayLoanMenu() {
        System.out.println("\n--- Loan Management ---");
        System.out.println("1. Add new loan account");
        System.out.println("2. View loan details");
        System.out.println("3. Update loan information");
        System.out.println("4. Remove loan account");
        System.out.println("5. Back to main menu");
    }

    private void handlePaymentManagement() {
        while (true) {
            displayPaymentMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    recordLoanPayment();
                    break;
                case 2:
                    calculateInterest();
                    break;
                case 3:
                    viewPaymentHistory();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayPaymentMenu() {
        System.out.println("\n--- Payment Management ---");
        System.out.println("1. Record loan payment");
        System.out.println("2. Calculate interest");
        System.out.println("3. View payment history");
        System.out.println("4. Back to main menu");
    }

    private void addNewLoan() {
        System.out.println("\n--- Add New Loan Account ---");
        int customerId = getIntInput("Enter customer ID: ");
        double loanAmount = getDoubleInput("Enter loan amount: ");
        double interestRate = getDoubleInput("Enter interest rate: ");
        LocalDate startDate = getDateInput("Enter start date (yyyy-MM-dd): ");
        LocalDate endDate = getDateInput("Enter end date (yyyy-MM-dd): ");
        String status = getStringInput("Enter loan status: ");

        Loan loan = new Loan(0, customerId, loanAmount, interestRate, startDate, endDate, status);
        loanService.addLoan(loan);
        System.out.println("Loan added successfully with ID: " + loan.getLoanId());
    }

    private void viewLoanDetails() {
        System.out.println("\n--- View Loan Details ---");
        int loanId = getIntInput("Enter loan ID: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            System.out.println(loan);
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void updateLoanInformation() {
        System.out.println("\n--- Update Loan Information ---");
        int loanId = getIntInput("Enter loan ID: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            System.out.println("Current loan details:");
            System.out.println(loan);
            
            double loanAmount = getDoubleInput("Enter new loan amount (or press enter to keep current): ");
            if (loanAmount != 0) {
                loan.setLoanAmount(loanAmount);
            }
            
            double interestRate = getDoubleInput("Enter new interest rate (or press enter to keep current): ");
            if (interestRate != 0) {
                loan.setInterestRate(interestRate);
            }
            
            String status = getStringInput("Enter new loan status (or press enter to keep current): ");
            if (!status.isEmpty()) {
                loan.setStatus(status);
            }
            
            loanService.updateLoan(loan);
            System.out.println("Loan updated successfully.");
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void removeLoanAccount() {
        System.out.println("\n--- Remove Loan Account ---");
        int loanId = getIntInput("Enter loan ID to remove: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            System.out.println("Are you sure you want to remove this loan account?");
            System.out.println(loan);
            String confirmation = getStringInput("Type 'YES' to confirm: ");
            if (confirmation.equalsIgnoreCase("YES")) {
                loanService.deleteLoan(loanId);
                System.out.println("Loan account removed successfully.");
            } else {
                System.out.println("Loan account removal cancelled.");
            }
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void recordLoanPayment() {
        System.out.println("\n--- Record Loan Payment ---");
        int loanId = getIntInput("Enter loan ID: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            double paymentAmount = getDoubleInput("Enter payment amount: ");
            LocalDate paymentDate = getDateInput("Enter payment date (yyyy-MM-dd): ");
            Payment payment = new Payment(0, loanId, paymentAmount, paymentDate);
            paymentService.addPayment(payment);
            System.out.println("Payment recorded successfully.");
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void calculateInterest() {
        System.out.println("\n--- Calculate Interest ---");
        int loanId = getIntInput("Enter loan ID: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            double interest = loanService.calculateInterest(loan);
            System.out.printf("Interest for loan ID %d: $%.2f%n", loanId, interest);
        } else {
            System.out.println("Loan not found.");
        }
    }

    private void viewPaymentHistory() {
        System.out.println("\n--- View Payment History ---");
        int loanId = getIntInput("Enter loan ID: ");
        Loan loan = loanService.getLoanById(loanId);
        if (loan != null) {
            List<Payment> payments = paymentService.getPaymentsByLoanId(loanId);
            if (!payments.isEmpty()) {
                System.out.println("Payment history for loan ID " + loanId + ":");
                for (Payment payment : payments) {
                    System.out.println(payment);
                }
            } else {
                System.out.println("No payments found for this loan.");
            }
        } else {
            System.out.println("Loan not found.");
        }
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateString = scanner.next();
                return LocalDate.parse(dateString, dateFormatter);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }
}