package com.taskmanager.taskflow.repository;

import com.taskmanager.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
