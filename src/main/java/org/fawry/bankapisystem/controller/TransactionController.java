package org.fawry.bankapisystem.controller;

import jakarta.validation.Valid;
import org.fawry.bankapisystem.dto.transaction.DepositRequistDTO;
import org.fawry.bankapisystem.dto.transaction.TransactionRequestDTO;
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
    public ResponseEntity<String> deposit(@RequestBody @Valid DepositRequistDTO depositRequistDTO){
        transactionService.deposit(depositRequistDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Succeed to deposit "+depositRequistDTO.getAmount()+" to card number "+ depositRequistDTO.getCardNumber());
    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO){
        transactionService.withdraw(transactionRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Succeed to withdraw "+transactionRequestDTO.getAmount()+" from card number "+ transactionRequestDTO.getCardNumber());
    }
}
