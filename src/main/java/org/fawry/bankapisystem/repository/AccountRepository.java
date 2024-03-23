package org.fawry.bankapisystem.repository;

import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByCardNumber(String cardNumber);
    boolean existsByCardNumberAndCVV(String cardNumber,String CVV);
    Account findByCardNumber(String cardNumber);

    @Query("FROM Account where user.id = :id")
    List<Account> getAccountsByUserId(Long id);

}
