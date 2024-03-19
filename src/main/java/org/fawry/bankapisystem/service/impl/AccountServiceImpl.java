package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.dto.AccountActivityResponseDto;
import org.fawry.bankapisystem.dto.AccountRequestDto;
import org.fawry.bankapisystem.dto.AccountResponseDto;
import org.fawry.bankapisystem.dto.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.mapper.AccountResponsMapper;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.AccountRepository;
import org.fawry.bankapisystem.service.AccountCreatorService;
import org.fawry.bankapisystem.service.AccountService;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    private final UserService userService;
    private final AccountCreatorService accountCreatorService;
    private final AccountRepository accountRepository;
    private final AccountResponsMapper accountResponsMapper;
    public AccountServiceImpl(UserService userService, AccountCreatorService accountCreatorService, AccountRepository accountRepository, AccountResponsMapper accountResponsMapper) {
        this.userService = userService;
        this.accountCreatorService = accountCreatorService;
        this.accountRepository = accountRepository;
        this.accountResponsMapper = accountResponsMapper;
    }



    @Override
    public AccountResponseDto createAccount() {
        User user = getCurrentUser();
        Account createdAccount = accountCreatorService.createAccount(user);
        return  accountResponsMapper.toResponse(createdAccount);
    }

    private User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByEmail = userService.findUserByEmail(userEmail);
        return userByEmail;
    }

    @Override
    public List<AccountResponseDto> getUserAccounts() {
        return null;
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
    public boolean isAccountExists(String cardNumber, String cvv) {
        return false;
    }

    @Override
    public List<AccountTransactionsHistoryResponseDto> accountTransactionHistroy(int cardId) {
        return null;
    }
}
