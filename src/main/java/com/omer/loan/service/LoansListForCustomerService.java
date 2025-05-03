package com.omer.loan.service;

import com.omer.loan.dto.LoanDTO;
import com.omer.loan.dto.LoanListForCustomerRequest;

import java.util.List;

public interface LoansListForCustomerService extends LoanBase<LoanListForCustomerRequest, List<LoanDTO>> {
}
