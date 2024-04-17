package org.fawry.bankapisystem.dto.account;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private String cardNumber;
    private String CVV;
    private double balance;
    private Timestamp createdAt;
    private Boolean status;

}
