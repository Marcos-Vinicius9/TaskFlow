package com.taskmanager.taskflow.repository;

import com.taskmanager.taskflow.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}
