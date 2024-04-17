package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.account.AccountActivityResponse;
import org.fawry.bankapisystem.dto.account.AccountResponse;
import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.model.Account;


import java.util.List;

public interface AccountService {
    AccountResponse createAccount();
    List<AccountResponse> getUserAccounts();
//    List<AccountResponse> getUserAccountsByUserId(Long id);
    List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long id);
    //
    AccountActivityResponse deactivateAccount(String cardNumber);
    AccountActivityResponse activateAccount(String cardNumber);

    boolean isAccountExistsByCardNumberAndCVV(String cardNumber,String cvv);
    boolean isAccountExistsByCardNumber(String cardNumber);
    //
    Account findAccountById(Long accountId);
}
