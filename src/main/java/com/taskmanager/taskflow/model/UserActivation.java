package com.taskmanager.taskflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "user_activations")
public class UserActivation {

    @Id
    @Column(name = "activation_token")
    private UUID tokenActivation;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    public UserActivation() {

    }

    public UserActivation(UUID tokenActivation, UUID userId) {
        this.tokenActivation = tokenActivation;
        this.userId = userId;
        this.expirationDate = LocalDateTime.now().plusMinutes(5);
    }

    public UUID getTokenActivation() {
        return tokenActivation;
    }

    public void setTokenActivation(UUID tokenActivation) {
        this.tokenActivation = tokenActivation;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }
}
