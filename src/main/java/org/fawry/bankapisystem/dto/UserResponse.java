package org.fawry.bankapisystem.dto;

import lombok.*;
import org.fawry.bankapisystem.dto.account.AccountResponse;
import org.fawry.bankapisystem.model.Account;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
   private String id;
   private String firstName;
   private String lastName;
   private String email;
   private String phoneNumber;
   private String city;
   private Boolean status;
}
