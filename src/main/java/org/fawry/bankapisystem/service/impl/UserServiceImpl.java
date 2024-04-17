package org.fawry.bankapisystem.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fawry.bankapisystem.dto.UserActivityResponse;
import org.fawry.bankapisystem.dto.UserResponse;
import org.fawry.bankapisystem.mapper.UserMapper;
import org.fawry.bankapisystem.model.Account;
import org.fawry.bankapisystem.model.User;
import org.fawry.bankapisystem.repository.UserRepository;
import org.fawry.bankapisystem.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

    public User getCurrentUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByEmail = findUserByEmail(userEmail);
        return userByEmail;
    }

    @Override
    public boolean existsUserEmailOrPhone(String email, String phoneNumber) {
        return userRepository.existsByEmail(email) || userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List <User> users = userRepository.findAll();
        List<UserResponse>userResponses =
                users.stream()
                        .map(user -> userMapper.toUserResponse(user))
                        .toList();
        return userResponses;
    }

    @Override
    public UserResponse getProfileInfo() {
        User user = getCurrentUser();
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserActivityResponse deactivateMyUser() {
        User user = getCurrentUser();
        user.setStatus(false);
        userRepository.save(user);
        return UserActivityResponse.builder()
                .message("User is deactivated, Login again to activate it")
                .build();
    }


}
