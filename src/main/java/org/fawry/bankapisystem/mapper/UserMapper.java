package org.fawry.bankapisystem.mapper;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.fawry.bankapisystem.dto.UserResponse;
import org.fawry.bankapisystem.dto.account.AccountResponse;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .city(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .build();
    }
}
