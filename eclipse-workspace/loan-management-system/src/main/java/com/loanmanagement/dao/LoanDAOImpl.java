package com.loanmanagement.dao;

import com.loanmanagement.model.Loan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOImpl implements LoanDAO {
    private Connection connection;

    public LoanDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLoan(Loan loan) {
        String sql = "INSERT INTO loans (customer_id, loan_amount, interest_rate, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, loan.getCustomerId());
            pstmt.setDouble(2, loan.getLoanAmount());
            pstmt.setDouble(3, loan.getInterestRate());
            pstmt.setDate(4, Date.valueOf(loan.getStartDate()));
            pstmt.setDate(5, Date.valueOf(loan.getEndDate()));
            pstmt.setString(6, loan.getStatus());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    loan.setLoanId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loan getLoanById(int loanId) {
        String sql = "SELECT * FROM loans WHERE loan_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Loan(
                        rs.getInt("loan_id"),
                        rs.getInt("customer_id"),
                        rs.getDouble("loan_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                loans.add(new Loan(
                    rs.getInt("loan_id"),
                    rs.getInt("customer_id"),
                    rs.getDouble("loan_amount"),
                    rs.getDouble("interest_rate"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void updateLoan(Loan loan) {
        String sql = "UPDATE loans SET customer_id = ?, loan_amount = ?, interest_rate = ?, start_date = ?, end_date = ?, status = ? WHERE loan_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loan.getCustomerId());
            pstmt.setDouble(2, loan.getLoanAmount());
            pstmt.setDouble(3, loan.getInterestRate());
            pstmt.setDate(4, Date.valueOf(loan.getStartDate()));
            pstmt.setDate(5, Date.valueOf(loan.getEndDate()));
            pstmt.setString(6, loan.getStatus());
            pstmt.setInt(7, loan.getLoanId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLoan(int loanId) {
        String sql = "DELETE FROM loans WHERE loan_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}