package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendConfirmationMessage (User user, String linkActivation) throws MessagingException;
}
