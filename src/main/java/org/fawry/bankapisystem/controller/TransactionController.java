package org.fawry.bankapisystem.controller;

import jakarta.validation.Valid;
import org.fawry.bankapisystem.dto.transaction.DepositRequest;
import org.fawry.bankapisystem.dto.transaction.WithdrawRequest;
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
    public ResponseEntity<String> deposit(@RequestBody @Valid DepositRequest depositRequest){
        transactionService.deposit(depositRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Succeed to deposit "+ depositRequest.getAmount()+" to card number "+ depositRequest.getCardNumber());
    }
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest){
        transactionService.withdraw(withdrawRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Succeed to withdraw "+ withdrawRequest.getAmount()+" from card number "+ withdrawRequest.getCardNumber());
    }
}
