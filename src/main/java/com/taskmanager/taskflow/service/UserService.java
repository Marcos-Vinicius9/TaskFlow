package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAllUsers();

    Boolean loginTest(String storedHash, String passwordUser);
    Optional<User> findUserById(UUID id);
    User findUserByEmail (String email);
    User registerUser(User user) throws MessagingException;
    Boolean confirmationAccount(String token);
}
