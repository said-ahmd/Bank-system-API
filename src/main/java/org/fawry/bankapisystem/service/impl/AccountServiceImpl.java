package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.dto.AccountActivityResponseDto;
import org.fawry.bankapisystem.dto.AccountRequestDto;
import org.fawry.bankapisystem.dto.AccountResponseDto;
import org.fawry.bankapisystem.dto.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.AccountRepository;
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
    private final AccountRepository accountRepository;
    private Random random = new Random();

    public AccountServiceImpl(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        Account account = new Account(
                getUniqueCardNumber(),
                generateCvv(),
                0.0,
                true,
                new Timestamp(System.currentTimeMillis()),
                user
        );
        accountRepository.save(account);
        return account;
    }

    ////
    public String getUniqueCardNumber(){
        String cardNumber = generateCardNumber();
        while (accountRepository.existsAccountByCardNumber(cardNumber)){
            cardNumber = generateCardNumber();
        }
        return cardNumber;
    }
    public String generateCvv() {
        StringBuilder cvvNumber = new StringBuilder();
        for(int i=0;i<4;++i){
            cvvNumber.append(random.nextInt(10));
        }
        return cvvNumber.toString();
    }

    private String generateCardNumber() {
        StringBuilder cvvNumber = new StringBuilder();
        for(int i=0;i<16;++i){
            cvvNumber.append(random.nextInt(10));
        }
        return cvvNumber.toString();
    }

    ///
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
