package org.fawry.bankapisystem.controller;

import jakarta.validation.Valid;
import org.fawry.bankapisystem.dto.auth.AtuthenticationRequest;
import org.fawry.bankapisystem.dto.auth.AuthenticationResponse;
import org.fawry.bankapisystem.dto.auth.RegisterRequest;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AtuthenticationRequest atuthenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(atuthenticationRequest));
    }

}
