package org.fawry.bankapisystem.repository;

import org.fawry.bankapisystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsAccountByCardNumber(String cardNumber);
}
