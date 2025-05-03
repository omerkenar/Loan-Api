package com.omer.loan.service;

import com.omer.loan.entity.Loan;
import com.omer.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("loanOwnerSecurityService")
public class LoanOwnerSecurityServiceImpl implements LoanOwnerSecurityService{
    private final LoanRepository loanRepository;
    @Override
    public boolean isLoanOwner(Long loanId, Long customerId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan == null) {
            return false;
        }
        return loan.getCustomer().getId().equals(customerId);
    }
}
