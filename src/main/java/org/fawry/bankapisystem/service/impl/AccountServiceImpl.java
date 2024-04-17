package org.fawry.bankapisystem.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.fawry.bankapisystem.dto.account.AccountActivityResponseDto;
import org.fawry.bankapisystem.dto.account.AccountResponseDto;
import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.mapper.AccountMapper;
import org.fawry.bankapisystem.mapper.TransactionMapper;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.Transaction;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.AccountRepository;
import org.fawry.bankapisystem.repository.TransactionRepository;
import org.fawry.bankapisystem.service.AccountCreatorService;
import org.fawry.bankapisystem.service.AccountService;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserService userService;
    private final AccountCreatorService accountCreatorService;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;

    public AccountServiceImpl(UserService userService, AccountCreatorService accountCreatorService, AccountRepository accountRepository, TransactionRepository transactionRepository, AccountMapper accountMapper, TransactionMapper transactionMapper) {
        this.userService = userService;
        this.accountCreatorService = accountCreatorService;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.accountMapper = accountMapper;
        this.transactionMapper = transactionMapper;
    }


    @Override
    public AccountResponseDto createAccount() {
        User user = userService.getCurrentUser();
        Account createdAccount = accountCreatorService.createAccount(user);

        return accountMapper.toResponse(createdAccount);
    }


    @Override
    public List<AccountResponseDto> getUserAccounts() {
        User user = userService.getCurrentUser();
        List<Account> allUserAcounts = accountRepository.getAccountsByUserId(user.getId());
        List<AccountResponseDto> accountsResponse = allUserAcounts.stream()
                .map(account -> accountMapper.toResponse(account))
                .toList();

        return accountsResponse;
    }

    @Override
    public List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long accountId) {
        User user = userService.getCurrentUser();
        List<Transaction> allUserTransactions = transactionRepository.getTransactionsHistoryByAccountId(accountId);
        List<AccountTransactionsHistoryResponseDto> transactionResponse = allUserTransactions.stream()
                .map(transaction -> transactionMapper.toResponse(transaction)).collect(Collectors.toList());
        return transactionResponse;
    }

    @Override
    public AccountActivityResponseDto deactivateAccount(int accountId) {
        return null;
    }

    @Override
    public AccountActivityResponseDto activateAccount(int accoutntId) {
        return null;
    }

    @Override
    public boolean isAccountExistsByCardNumberAndCVV(String cardNumber, String cvv) {
        return accountRepository.existsByCardNumberAndCVV(cardNumber, cvv);
    }

    @Override
    public boolean isAccountExistsByCardNumber(String cardNumber) {
        return accountRepository.existsByCardNumber(cardNumber);
    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()-> new EntityNotFoundException("Account not found"));
    }

}
