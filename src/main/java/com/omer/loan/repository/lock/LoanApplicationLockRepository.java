package com.omer.loan.repository.lock;

import com.omer.loan.entity.LoanApplicationLock;
import com.omer.loan.entity.LockStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanApplicationLockRepository extends JpaRepository<LoanApplicationLock, Long> {

    Optional<LoanApplicationLock> findByCustomerIdAndStatus(Long customerId, LockStatus status);

}
