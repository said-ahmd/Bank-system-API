package org.fawry.bankapisystem.mapper;

import org.fawry.bankapisystem.dto.auth.AuthenticationResponse;
import org.fawry.bankapisystem.dto.auth.RegisterRequest;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.model.enumTypes.RoleType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class AuthenticationMapper {
    private final PasswordEncoder passwordEncoder;

    public AuthenticationMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(RegisterRequest registerRequest){
        return User.builder()
            .firstName(registerRequest.getFirstName())
            .lastName(registerRequest.getLastName())
            .email(registerRequest.getEmail())
            .phoneNumber(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .roleType(RoleType.USER)
            .address(registerRequest.getAddress())
            .status(true)
            .createdAt(new Timestamp(System.currentTimeMillis()))
            .build();
    }

}
