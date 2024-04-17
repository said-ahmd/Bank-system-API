package org.fawry.bankapisystem.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtuthenticationRequest {

    @NotEmpty(message = "Email shouldn't be empty.")
    @Email(message = "Email should be valid.")
    private String email;
    @NotEmpty(message = "Password shouldn't be empty.")
    private String password;

}
