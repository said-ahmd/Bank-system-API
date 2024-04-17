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
        return new User(
                registerRequest.getFirstName(),
                registerRequest.getFirstName(),
                registerRequest.getEmail(),
                registerRequest.getPhoneNumber(),
                RoleType.ADMIN,
                passwordEncoder.encode(registerRequest.getPassword()),
                registerRequest.getAddress(),
                true,
                new Timestamp(System.currentTimeMillis())
        );
    }

}
