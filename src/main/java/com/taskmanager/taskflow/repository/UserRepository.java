package com.taskmanager.taskflow.repository;

import com.taskmanager.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT u FROM users u WHERE u.email = :email")
    User findUserByEmail(String email);
}
