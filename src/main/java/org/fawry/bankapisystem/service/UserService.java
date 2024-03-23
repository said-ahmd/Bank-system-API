package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    User saveUser(User user);
    User getCurrentUser();
    boolean existsUserEmailorPhone(String email, String phoneNumber);
    ///deactivate - activate -  list accounts


}
