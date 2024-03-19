package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.model.User;

public interface UserService {
    User findUserByEmail(String email);

    User saveUser(User user);
    User getCurrentUser();
    ///

}
