package com.loanmanagement.service;

import com.loanmanagement.dao.PaymentDAO;
import com.loanmanagement.model.Payment;
import java.util.List;

public class PaymentService {
    private PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public void addPayment(Payment payment) {
        paymentDAO.addPayment(payment);
    }

    public List<Payment> getPaymentsByLoanId(int loanId) {
        return paymentDAO.getPaymentsByLoanId(loanId);
    }
}