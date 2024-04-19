package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.transaction.DepositRequest;
import org.fawry.bankapisystem.dto.transaction.WithdrawRequest;

public interface TransactionService {
    public void deposit(DepositRequest request);
    public void withdraw(WithdrawRequest request);
}
