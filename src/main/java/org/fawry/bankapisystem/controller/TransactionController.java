package org.fawry.bankapisystem.controller;

import org.fawry.bankapisystem.dto.GeneralResponseDto;
import org.fawry.bankapisystem.dto.TransactionRequestDTO;
import org.fawry.bankapisystem.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {
    final private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<GeneralResponseDto> deposit(@RequestBody TransactionRequestDTO transactionRequestDTO){
        transactionService.deposit(transactionRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    new GeneralResponseDto(true)
                );
    }
    @PostMapping("/withdraw")
    public ResponseEntity<GeneralResponseDto> withdraw(@RequestBody TransactionRequestDTO transactionRequestDTO){
        transactionService.withdraw(transactionRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                    new GeneralResponseDto(true)
                );
    }
}
