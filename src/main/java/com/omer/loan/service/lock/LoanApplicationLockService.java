package com.omer.loan.service.lock;


import com.omer.loan.entity.LoanApplicationLock;
import com.omer.loan.entity.LockStatus;
import com.omer.loan.handlers.exceptions.ApiException;
import com.omer.loan.repository.lock.LoanApplicationLockRepository;
import com.omer.loan.utils.constants.LoanServiceConstants;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class LoanApplicationLockService {

    private final LoanApplicationLockRepository lockRepository;

    @Transactional
    public void createLock(Long customerId) {
        lockRepository.findByCustomerIdAndStatus(customerId, LockStatus.IN_PROGRESS)
                .ifPresent(lock -> {
                    throw new ApiException(LoanServiceConstants.ERROR_ACTIVE_LOAN_APPLICATION, HttpStatus.CONFLICT);
                });

        LoanApplicationLock newLock = new LoanApplicationLock();
        newLock.setCustomerId(customerId);
        newLock.setStatus(LockStatus.IN_PROGRESS);
        newLock.setCreatedDate(LocalDateTime.now());
        lockRepository.save(newLock);
    }

    public void checkLockExists(Long customerId) {
        lockRepository.findByCustomerIdAndStatus(customerId, LockStatus.IN_PROGRESS)
                .ifPresent(lock -> {
                    throw new ApiException(LoanServiceConstants.ERROR_ACTIVE_LOCK_EXISTS, HttpStatus.CONFLICT);
                });
    }

    @Transactional
    public void markLockAsDone(Long customerId) {
        LoanApplicationLock lock = lockRepository.findByCustomerIdAndStatus(customerId, LockStatus.IN_PROGRESS)
                .orElseThrow(() -> new ApiException(LoanServiceConstants.ERROR_ACTIVE_LOCK_NOT_FOUND, HttpStatus.NOT_FOUND));

        lock.setStatus(LockStatus.DONE);
        lock.setUpdatedDate(LocalDateTime.now());
        lockRepository.save(lock);
    }

    @Transactional
    public void markLockAsFailed(Long customerId) {
        lockRepository.findByCustomerIdAndStatus(customerId, LockStatus.IN_PROGRESS)
                .ifPresent(lock -> {
                    lock.setStatus(LockStatus.FAILED);
                    lock.setUpdatedDate(LocalDateTime.now());
                    lockRepository.save(lock);
                });
    }
}


