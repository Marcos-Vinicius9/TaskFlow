package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.Task;
import com.taskmanager.taskflow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;


    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }


    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }


    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    @Transactional
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }


    public Optional<Task> getTasksByUserId(UUID userId) {
        return taskRepository.findById(userId);
    }


}
