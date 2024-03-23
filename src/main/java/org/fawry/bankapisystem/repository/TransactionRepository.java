package org.fawry.bankapisystem.repository;

import org.fawry.bankapisystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query("from Transaction where account.id = :id")
    List<Transaction> getTransactionsHistoryByAccountId(Long id);

}
