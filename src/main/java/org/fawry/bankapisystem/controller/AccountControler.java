package org.fawry.bankapisystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fawry.bankapisystem.dto.account.AccountActivityResponse;
import org.fawry.bankapisystem.dto.account.AccountResponse;
import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank/account")
@RequiredArgsConstructor
public class AccountControler {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(){

        AccountResponse createdAccount = accountService.createAccount();
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdAccount);
    }
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getUserAccounts(){

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

    @PutMapping(value = "/{cardNumber}", params = "activate")
    public ResponseEntity<AccountActivityResponse>activateMyAccoutn(@Valid @PathVariable String cardNumber){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.activateAccount(cardNumber));
    }
    @PutMapping(value = "/{cardNumber}", params = "deactivate")
    public ResponseEntity<AccountActivityResponse>deActivateMyAccoutn(@Valid @PathVariable String cardNumber){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.deactivateAccount(cardNumber));
    }
}
