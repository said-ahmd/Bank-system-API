package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.dto.transaction.DepositRequest;
import org.fawry.bankapisystem.dto.transaction.WithdrawRequest;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.Transaction;
import org.fawry.bankapisystem.model.enumTypes.TransactionType;
import org.fawry.bankapisystem.repository.AccountRepository;
import org.fawry.bankapisystem.repository.TransactionRepository;
import org.fawry.bankapisystem.service.AccountService;
import org.fawry.bankapisystem.service.TransactionService;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransactionServiceImpl implements TransactionService {

    final private AccountRepository accountRepository;
    final private UserService userService;
    final private TransactionRepository transactionRepository;
    final private AccountService accountService;

    public TransactionServiceImpl(AccountRepository accountRepository, UserService userService, TransactionRepository transactionRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public void deposit(DepositRequest request) {
        Account account = validateAccountForDeposit(request);
        doDeposit(account,request.getAmount());
    }


    @Override
    public void withdraw(WithdrawRequest request) {
        Account account = validateAccountForWithdraw(request);
        doWithdraw(account,request.getAmount());
    }

    public void doDeposit(Account account,double amount){
        account.setBalance(amount+account.getBalance());
        String transactionDescriptions="Deposit "+amount+" to "+account.getUser().getEmail();

        transactionRepository.save(Transaction.builder()
                        .transactionType(TransactionType.DEPOSIT)
                        .amount(amount)
                        .description(transactionDescriptions)
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .account(account)
                .build());
    }

    private void doWithdraw(Account account, double amount) {

        if (account.getBalance() < amount){
            throw new IllegalArgumentException("The balance isn't enough to withdraw "+amount);
        }

        account.setBalance(amount-account.getBalance());
        String transactionDescriptions="Withdraw "+amount+" from "+account.getUser().getEmail();


        transactionRepository.save(Transaction.builder()
                .transactionType(TransactionType.WITHDRAW)
                .amount(amount)
                .description(transactionDescriptions)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .account(account)
                .build());
    }

    private Account validateAccountForDeposit(DepositRequest request) {
        String cardNumber = request.getCardNumber();
        boolean isAccountExisits = accountRepository.existsByCardNumber(cardNumber);
        if(!isAccountExisits){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't exist.");
        }
        Account account = accountRepository.findByCardNumber(cardNumber);
        if(!account.getStatus() || !account.getUser().getStatus()){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't valid right now.");
        }
        return account;
    }

    private Account validateAccountForWithdraw(WithdrawRequest request){

        String CVV = request.getCVV();
        String cardNumber = request.getCardNumber();

        boolean isAccountExists = accountService.isAccountExistsByCardNumberAndCVV(cardNumber,CVV);
        if(!isAccountExists){
            throw new IllegalArgumentException("There is a wrong in the card number: "+cardNumber+" or in the CVV: "+CVV);
        }

        Account account = accountRepository.findByCardNumber(cardNumber);
        if(!account.getStatus() || !account.getUser().getStatus()){
            throw new IllegalArgumentException("The card with number "+cardNumber+" isn't valid right now.");
        }

        return account;
    }


}
