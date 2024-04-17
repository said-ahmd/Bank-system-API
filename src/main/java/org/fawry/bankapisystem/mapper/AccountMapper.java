package org.fawry.bankapisystem.mapper;

import org.fawry.bankapisystem.dto.account.AccountResponse;
import org.fawry.bankapisystem.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponse toResponse(Account account){
        return AccountResponse.builder()
                .cardNumber(account.getCardNumber())
                .CVV(account.getCVV())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .status(account.getStatus())
                .build();
    }
}
