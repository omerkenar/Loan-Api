package com.omer.loan.service;
// loan marker
public interface LoanBase<T, R> {
    R execute(T request);
}
