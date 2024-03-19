package org.fawry.bankapisystem.dto;


public class TransactionRequestDTO {
    private String cardNumber;
    private String CVV;
    private double amount;
    public TransactionRequestDTO(String cardNumber, String CVV, double amount) {
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.amount = amount;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
