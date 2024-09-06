package com.loanmanagement.service;

import com.loanmanagement.dao.LoanDAO;
import com.loanmanagement.model.Loan;
import java.util.List;

public class LoanService {
    private LoanDAO loanDAO;

    public LoanService(LoanDAO loanDAO) {
        this.loanDAO = loanDAO;
    }

    public void addLoan(Loan loan) {
        loanDAO.addLoan(loan);
    }

    public Loan getLoanById(int loanId) {
        return loanDAO.getLoanById(loanId);
    }

    public List<Loan> getAllLoans() {
        return loanDAO.getAllLoans();
    }

    public void updateLoan(Loan loan) {
        loanDAO.updateLoan(loan);
    }

    public void deleteLoan(int loanId) {
        loanDAO.deleteLoan(loanId);
    }

    public double calculateInterest(Loan loan) {
        // Simple interest calculation
        return (loan.getLoanAmount() * loan.getInterestRate() * 
                (loan.getEndDate().getYear() - loan.getStartDate().getYear())) / 100;
    }
}