package com.omer.loan.dto;

import com.omer.loan.entity.LoanInstallment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private int paidCount;
    private BigDecimal totalSpent;
    private BigDecimal totalPrincipalPaid;
    private List<LoanInstallment> installments;
}
