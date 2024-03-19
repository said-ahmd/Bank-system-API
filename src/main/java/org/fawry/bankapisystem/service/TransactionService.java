package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.TransactionRequestDTO;

public interface TransactionService {
    public void deposit(TransactionRequestDTO request);
    public void withdraw(TransactionRequestDTO request);
}
