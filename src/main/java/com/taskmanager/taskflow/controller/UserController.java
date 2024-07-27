package com.taskmanager.taskflow.controller;

import com.taskmanager.taskflow.config.SecurityConfig;
import com.taskmanager.taskflow.exception.InvalidOrExpiredTokenException;
import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.service.UserService;
import jakarta.mail.MessagingException;
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

    @Autowired
    private SecurityConfig securityConfig;

    @GetMapping
    public ResponseEntity<Object> findAllUsers(){
        return ResponseEntity.accepted().body(this.userService.findAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) throws MessagingException {
        return ResponseEntity.accepted().body(this.userService.registerUser(user));
    }
    @PostMapping("/login")
    public ResponseEntity<Object> testLogin(@RequestBody User user){
        User userVerif = userService.findUserByEmail(user.getEmail());
        String storedHash = userService.findUserByEmail(user.getEmail()).getPassword();
        return ResponseEntity.accepted().body(this.userService.loginTest(storedHash, user.getPassword()) && userVerif.isActive());
    }

    @GetMapping("/activation/{token}")
    public ResponseEntity<Object> activateAccount(@PathVariable String token) {
        if(userService.confirmationAccount(token)){
            return ResponseEntity.accepted().body("Conta ativada com sucesso.");
        }
        throw new InvalidOrExpiredTokenException("Token expirado ou inválido.");
    }



}
