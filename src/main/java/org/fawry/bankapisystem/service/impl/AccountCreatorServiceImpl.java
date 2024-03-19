package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.AccountRepository;
import org.fawry.bankapisystem.service.AccountCreatorService;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

@Service
public class AccountCreatorServiceImpl implements AccountCreatorService {
   final private UserService userService;
   final private AccountRepository accountRepository;
   private Random random = new Random();

    public AccountCreatorServiceImpl(UserService userService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(User user) {
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
}
