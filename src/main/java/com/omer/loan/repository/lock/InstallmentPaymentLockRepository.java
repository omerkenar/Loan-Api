package com.omer.loan.repository.lock;

import com.omer.loan.entity.InstallmentPaymentLock;
import com.omer.loan.entity.LockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstallmentPaymentLockRepository extends JpaRepository<InstallmentPaymentLock, Long> {

    Optional<InstallmentPaymentLock> findByLoanIdAndStatus(Long loanId, LockStatus status);

    Optional<InstallmentPaymentLock> findByCustomerIdAndStatus(Long customerId, LockStatus status);
}
