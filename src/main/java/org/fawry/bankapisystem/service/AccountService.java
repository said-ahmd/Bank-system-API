package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.AccountActivityResponseDto;
import org.fawry.bankapisystem.dto.AccountResponseDto;
import org.fawry.bankapisystem.dto.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.model.Account;


import java.util.List;

public interface AccountService {
    Account createAccount();
    List<AccountResponseDto> getUserAccounts();
    //
    AccountActivityResponseDto deactivateAccount(int accountId);
    AccountActivityResponseDto activateAccount(int accoutntId);

    boolean isAccountExists(String cardNumber,String cvv);
    //
    List<AccountTransactionsHistoryResponseDto>accountTransactionHistroy(int cardId);
}
