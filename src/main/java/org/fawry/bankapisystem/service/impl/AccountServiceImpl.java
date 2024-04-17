package org.fawry.bankapisystem.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.fawry.bankapisystem.dto.account.AccountActivityResponse;
import org.fawry.bankapisystem.dto.account.AccountResponse;
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

import java.sql.Timestamp;
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
    public AccountResponse createAccount() {
        User user = userService.getCurrentUser();
        Account createdAccount = accountCreatorService.createAccount(user);

        return accountMapper.toResponse(createdAccount);
    }


    @Override
    public List<AccountResponse> getUserAccounts() {
        User user = userService.getCurrentUser();
        List<Account> allUserAcounts = accountRepository.getAccountsByUserId(user.getId());
        List<AccountResponse> accountsResponse = allUserAcounts.stream()
                .map(account -> accountMapper.toResponse(account))
                .toList();

        return accountsResponse;
    }

//    @Override
//    public List<AccountResponse> getUserAccountsByUserId(Long id) {
//        List<Account> accounts = accountRepository.getAccountsByUserId(id);
//        System.out.println(accounts);
//        List<AccountResponse> accountResponses = accounts.stream()
//                .map(account -> accountMapper.toResponse(account))
//                .toList();
//        System.out.println(accountResponses);
//        return List.of(AccountResponse.builder().build());
//    }

    @Override
    public List<AccountTransactionsHistoryResponseDto> getUserAccountTransactions(Long accountId) {
        User user = userService.getCurrentUser();
        List<Transaction> allUserTransactions = transactionRepository.getTransactionsHistoryByAccountId(accountId);
        List<AccountTransactionsHistoryResponseDto> transactionResponse = allUserTransactions.stream()
                .map(transaction -> transactionMapper.toResponse(transaction)).collect(Collectors.toList());
        return transactionResponse;
    }

    @Override
    public AccountActivityResponse deactivateAccount(String cardNumber) {
        Account account = accountRepository.findByCardNumber(cardNumber);
        checkUserAuthentication(account);

        if(account.getStatus()){
            account.setStatus(false);
            accountRepository.save(account);
        }
        return AccountActivityResponse.builder()
                .message("The account deactivated")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }



    @Override
    public AccountActivityResponse activateAccount(String  cardNumber) {
        Account account = accountRepository.findByCardNumber(cardNumber);
        checkUserAuthentication(account);

        if(!account.getStatus()){
            account.setStatus(true);
            accountRepository.save(account);
        }
        return AccountActivityResponse.builder()
                .message("The account activated")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
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

    private void checkUserAuthentication(Account account) {
        User user = userService.getCurrentUser();
        if (!account.getUser().equals(user)){
            throw new IllegalArgumentException("Not Auth To See Transaction History");
        }
    }
}
