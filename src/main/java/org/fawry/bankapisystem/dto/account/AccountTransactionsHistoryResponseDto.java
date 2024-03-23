package org.fawry.bankapisystem.dto.account;

import org.fawry.bankapisystem.model.enumTypes.TransactionType;

import java.sql.Timestamp;

public class AccountTransactionsHistoryResponseDto {
    private Timestamp createdAt;
    private Double amount;
    private TransactionType transactionType;
    private String description;

    public AccountTransactionsHistoryResponseDto(Timestamp createdAt, Double amount, TransactionType transactionType, String description) {
        this.createdAt = createdAt;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
