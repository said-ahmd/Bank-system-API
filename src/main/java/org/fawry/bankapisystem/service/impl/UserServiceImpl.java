package org.fawry.bankapisystem.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.UserRepository;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new EntityNotFoundException("user not found"));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
