package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User registerUser(User user);
}
