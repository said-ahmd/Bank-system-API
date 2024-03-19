package org.fawry.bankapisystem.controller;

import org.fawry.bankapisystem.dto.GeneralResponseDto;
import org.fawry.bankapisystem.service.AccountCreatorService;
import org.fawry.bankapisystem.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class AccountControler {

    private final AccountService accountService;

    public AccountControler(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/account")
    public ResponseEntity<GeneralResponseDto> createAccount(){
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(
                        new GeneralResponseDto(
                            true
                            , accountService.createAccount()
                        )
                    );
    }
}
