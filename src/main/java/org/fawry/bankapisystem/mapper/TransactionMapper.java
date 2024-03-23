package org.fawry.bankapisystem.mapper;

import org.fawry.bankapisystem.dto.account.AccountTransactionsHistoryResponseDto;
import org.fawry.bankapisystem.model.Transaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public AccountTransactionsHistoryResponseDto toResponse(Transaction transaction){
        return new AccountTransactionsHistoryResponseDto(
                transaction.getCreatedAt(),
                transaction.getAmount(),
                transaction.getTransactionType(),
                transaction.getDescription()
        );
    }
}
