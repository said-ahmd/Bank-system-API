package org.fawry.bankapisystem.service;

import org.fawry.bankapisystem.dto.UserActivityResponse;
import org.fawry.bankapisystem.dto.UserResponse;
import org.fawry.bankapisystem.model.User;

import java.util.List;

public interface UserService {
    User findUserByEmail(String email);

    User saveUser(User user);
    User getCurrentUser();
    boolean existsUserEmailOrPhone(String email, String phoneNumber);
    ///deactivate - activate -  list accounts
    List<UserResponse> getAllUsers();

    UserResponse getProfileInfo();
    UserActivityResponse deactivateMyUser();

}
