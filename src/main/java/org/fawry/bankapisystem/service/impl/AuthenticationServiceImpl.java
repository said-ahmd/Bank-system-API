package org.fawry.bankapisystem.service.impl;

import org.fawry.bankapisystem.dto.auth.AtuthenticationRequest;
import org.fawry.bankapisystem.dto.auth.AuthenticationResponse;
import org.fawry.bankapisystem.dto.auth.RegisterRequest;
import org.fawry.bankapisystem.mapper.AuthenticationMapper;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.security.JWTService;
import org.fawry.bankapisystem.service.AuthenticationService;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationMapper authenticationMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(AuthenticationMapper authenticationMapper, JWTService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationMapper = authenticationMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if(userService.existsUserEmailOrPhone(registerRequest.getEmail(),registerRequest.getPhoneNumber())){
            throw new IllegalArgumentException("The email or phone number already exists.");
        }

        User user = authenticationMapper.toEntity(registerRequest);
        userService.saveUser(user);
        String token = jwtService.generateToken(user);
        
        sendValidationEmail(user);
        
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private void sendValidationEmail(User user) {
        //createAndSaveToken()
    }

    @Override
    public AuthenticationResponse authenticate(AtuthenticationRequest atuthenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        atuthenticationRequest.getEmail(),
                        atuthenticationRequest.getPassword()
                )
        );

        User user = ((User)authenticate.getPrincipal());
        String token = jwtService.generateToken(user);
        System.out.println(token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
