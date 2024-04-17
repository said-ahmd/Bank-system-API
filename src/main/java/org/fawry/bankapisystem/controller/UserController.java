package org.fawry.bankapisystem.controller;

import lombok.RequiredArgsConstructor;
import org.fawry.bankapisystem.dto.UserActivityResponse;
import org.fawry.bankapisystem.dto.UserResponse;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bank/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @GetMapping()
    public ResponseEntity<UserResponse> getUserInfo() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getProfileInfo());
    }

    @PutMapping
    public ResponseEntity<UserActivityResponse> deactivateMyUser(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.deactivateMyUser());
    }
}
