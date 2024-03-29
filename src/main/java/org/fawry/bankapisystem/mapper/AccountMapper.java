package org.fawry.bankapisystem.mapper;

import org.fawry.bankapisystem.dto.account.AccountResponseDto;
import org.fawry.bankapisystem.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto toResponse(Account account){
        return new AccountResponseDto(
                account.getCardNumber(),
                account.getCVV(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getStatus()
        );
    }
}
