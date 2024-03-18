package org.fawry.bankapisystem.controller;

import org.fawry.bankapisystem.dto.AtuthenticationRequest;
import org.fawry.bankapisystem.dto.AuthenticationResponse;
import org.fawry.bankapisystem.dto.RegisterRequest;
import org.fawry.bankapisystem.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AtuthenticationRequest atuthenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(atuthenticationRequest));
    }

}
