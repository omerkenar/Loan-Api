package com.omer.loan.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PayInstallmentRequest {
    private Long loanId;
    private BigDecimal payAmount;
}