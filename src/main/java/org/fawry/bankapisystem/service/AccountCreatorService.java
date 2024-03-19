package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;

public interface AccountCreatorService {
    Account createAccount(User user);
}
