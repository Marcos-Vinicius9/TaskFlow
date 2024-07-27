package com.taskmanager.taskflow.repository;

import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.model.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserActivationRepository extends JpaRepository<UserActivation, UUID> {
    @Query("SELECT ua FROM user_activations ua WHERE ua.tokenActivation = :token")
    UserActivation userActivation(@Param("token") UUID token);

}
