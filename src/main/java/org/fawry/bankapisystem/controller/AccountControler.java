package org.fawry.bankapisystem.controller;

import org.fawry.bankapisystem.dto.account.AccountResponseDto;
import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/account")
public class AccountControler {

    private final AccountService accountService;

    public AccountControler(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(){

        AccountResponseDto createdAccount = accountService.createAccount();
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdAccount);
    }
    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getUserAccounts(){
        List<AccountResponseDto> userAccounts = accountService.getUserAccounts();

        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(accountService.getUserAccounts());
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<List<AccountTransactionsHistoryResponseDto>> getAccountHistory(@PathVariable Long accountId){
        List<AccountTransactionsHistoryResponseDto> transactionHistory = accountService.getUserAccountTransactions(accountId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionHistory);
    }
//    public ResponseEntity<List<>>
//    @GetMapping
//    public ResponseEntity<List<AccountResponseDto>> getAccountById(@PathVariable Long id){
//        List<AccountResponseDto> userAccounts = accountService.getUserAccounts();
//
//        return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(userAccounts);
//    }
}
