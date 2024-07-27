package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.UserActivation;

public interface UserActivationService {
    void addUserActivationToken(UserActivation user);
    Boolean confirmActivationAccount(String token);
}
