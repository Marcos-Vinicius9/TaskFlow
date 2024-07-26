package com.taskmanager.taskflow.controller;

import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Object> findAllUsers(){
        return ResponseEntity.accepted().body(this.userService.findAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user){
        return ResponseEntity.accepted().body(this.userService.registerUser(user));
    }


}
