package org.fawry.bankapisystem.dto.account;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountActivityResponse {
    private String message;
    private Timestamp timestamp;
}
