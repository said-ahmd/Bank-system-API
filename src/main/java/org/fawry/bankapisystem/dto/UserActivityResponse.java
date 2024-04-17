package org.fawry.bankapisystem.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityResponse {
    private String message;
    private Timestamp timestamp;
}
