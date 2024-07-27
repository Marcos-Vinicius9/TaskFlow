package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    Boolean loginTest(String storedHash, String passwordUser);

    User findUserByEmail (String email);
    User registerUser(User user);
}
