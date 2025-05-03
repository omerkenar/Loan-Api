package com.omer.loan.service;

import com.omer.loan.dto.ListInstallmentRequest;
import com.omer.loan.dto.LoanInstallmentDTO;
import com.omer.loan.entity.LoanInstallment;
import com.omer.loan.repository.LoanInstallmentRepository;
import com.omer.loan.utils.mapper.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InstallmentListServiceImpl implements InstallmentListService {

    private final LoanInstallmentRepository installmentRepository;


    @Override
    @PreAuthorize("hasRole('ADMIN') or @loanOwnerSecurityService.isLoanOwner(#request.loanId, principal.customerId)")
    @Cacheable(value = "installmentsCache", key = "#request.loanId")
    public List<LoanInstallmentDTO> execute(ListInstallmentRequest request) {
        Page<LoanInstallment> installmentsPage = installmentRepository.findByLoan_IdOrderByDueDateAsc(request.getLoanId(),request.getPageable());
        return installmentsPage.map(LoanMapper::entityToInstallmentDTO).getContent();
    }
}
