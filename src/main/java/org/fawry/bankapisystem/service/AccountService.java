package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.account.AccountActivityResponseDto;
import org.fawry.bankapisystem.dto.account.AccountResponseDto;
import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.model.Account;


import java.util.List;

public interface AccountService {
    AccountResponseDto createAccount();
    List<AccountResponseDto> getUserAccounts();
    List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long id);
    //
    AccountActivityResponseDto deactivateAccount(int accountId);
    AccountActivityResponseDto activateAccount(int accoutntId);

    boolean isAccountExistsByCardNumberAndCVV(String cardNumber,String cvv);
    boolean isAccountExistsByCardNumber(String cardNumber);
    //
    List<AccountTransactionsHistoryResponseDto>accountTransactionHistroy(int cardId);
}
