package com.loanmanagement.dao;

import com.loanmanagement.model.Payment;
import java.util.List;

public interface PaymentDAO {
    void addPayment(Payment payment);
    List<Payment> getPaymentsByLoanId(int loanId);
}