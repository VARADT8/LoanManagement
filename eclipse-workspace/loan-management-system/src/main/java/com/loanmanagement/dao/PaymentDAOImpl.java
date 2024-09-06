package com.loanmanagement.dao;

import com.loanmanagement.model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private Connection connection;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments (loan_id, payment_amount, payment_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, payment.getLoanId());
            pstmt.setDouble(2, payment.getPaymentAmount());
            pstmt.setDate(3, Date.valueOf(payment.getPaymentDate()));
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    payment.setPaymentId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> getPaymentsByLoanId(int loanId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE loan_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    payments.add(new Payment(
                        rs.getInt("payment_id"),
                        rs.getInt("loan_id"),
                        rs.getDouble("payment_amount"),
                        rs.getDate("payment_date").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}