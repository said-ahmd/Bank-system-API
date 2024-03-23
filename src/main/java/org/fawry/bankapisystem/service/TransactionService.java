package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.transaction.DepositRequistDTO;
import org.fawry.bankapisystem.dto.transaction.TransactionRequestDTO;

public interface TransactionService {
    public void deposit(DepositRequistDTO request);
    public void withdraw(TransactionRequestDTO request);
}
