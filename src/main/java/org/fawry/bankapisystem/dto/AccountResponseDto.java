package org.fawry.bankapisystem.dto;

import java.sql.Timestamp;

public class AccountResponseDto {
    private String cardNumber;
    private String CVV;
    private double balance;
    private Timestamp createdAt;
    private Boolean status;

    public AccountResponseDto( String cardNumber, String CVV, double balance, Timestamp createdAt, Boolean status) {
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        this.balance = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
