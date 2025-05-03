package com.omer.loan.service;

import com.omer.loan.dto.ListInstallmentRequest;
import com.omer.loan.dto.LoanInstallmentDTO;

import java.util.List;

public interface InstallmentListService extends LoanBase<ListInstallmentRequest,List<LoanInstallmentDTO>> {
}
