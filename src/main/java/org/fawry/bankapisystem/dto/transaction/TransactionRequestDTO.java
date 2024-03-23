package org.fawry.bankapisystem.dto.transaction;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TransactionRequestDTO {
    @Size(min = 16, max = 16, message = "The card number should be 16 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The card number should be only numbers.")
    private String cardNumber;
    @Size(min = 4, max = 4 , message = "The CVV should be 4 numbers.")
    @Pattern(regexp = "^[0-9]+$", message = "The CVV should be only numbers.")
    private String CVV;

    @Positive(message = "The amount should be positive.")
    @Min(value = 5, message = "The amount should be greater than 5.")
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
