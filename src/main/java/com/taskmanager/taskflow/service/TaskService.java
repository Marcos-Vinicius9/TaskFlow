package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    Task saveTask(Task task);

    Optional<Task> getTaskById(UUID id);

    List<Task> getAllTasks();

    void deleteTask(UUID id);

    Optional<Task> getTasksByUserId(UUID userId);
}
