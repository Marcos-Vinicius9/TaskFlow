package com.taskmanager.taskflow.controller;

import com.taskmanager.taskflow.model.Task;
import com.taskmanager.taskflow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.saveTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.getTaskById(id));
    }

    @GetMapping
    public ResponseEntity<Object> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable UUID id, @RequestBody Task task) {
        if (taskService.getTaskById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(this.taskService.saveTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable UUID id) {
        if (taskService.getTaskById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getTasksByUserId(@PathVariable UUID userId) {
        return ResponseEntity.accepted().body(this.taskService.getTasksByUserId(userId));
    }
}
