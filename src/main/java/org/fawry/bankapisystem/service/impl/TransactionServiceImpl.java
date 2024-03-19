package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.dto.TransactionRequestDTO;
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
    public void deposit(TransactionRequestDTO request) {
        Account account = validateAccount(request);
        doDeposit(account,request.getAmount());
    }
    @Override
    public void withdraw(TransactionRequestDTO request) {
        Account account = validateAccount(request);
        doWithdraw(account,request.getAmount());

    }

    public void doDeposit(Account account,double amount){
        account.setBalance(amount+account.getBalance());
        String transactionDescriptions="Deposit "+amount+" to "+account.getUser().getEmail();

        Transaction transaction=new Transaction(
                TransactionType.DEPOSIT,
                amount,
                transactionDescriptions,
                new Timestamp(System.currentTimeMillis()),
                account
        );

        transactionRepository.save(transaction);
    }

    private void doWithdraw(Account account, double amount) {
        if (account.getBalance()< amount){
            throw new IllegalArgumentException("The balance isn't enough to withdraw "+account);
        }
        String transactionDescriptions="Withdraw "+amount+" from "+account.getUser().getEmail();

        account.setBalance(account.getBalance()-amount);
        Transaction transaction = new Transaction(
                TransactionType.WITHDRAW,
                amount,
                transactionDescriptions,
                new Timestamp(System.currentTimeMillis()),
                account
        );
        transactionRepository.save(transaction);
    }

    Account validateAccount(TransactionRequestDTO request){

        String CVV = request.getCVV();
        String cardNumber = request.getCardNumber();

        boolean isAccountExists = accountService.isAccountExists(cardNumber,CVV);
        if(!isAccountExists){
            throw  new IllegalArgumentException("the card is not valid..");
        }

        Account account = accountRepository.findByCardNumber(cardNumber);
        if(!account.getStatus() || !account.getUser().getStatus()){
            throw  new IllegalArgumentException("the card is not valid..");
        }

        return account;
    }


}
