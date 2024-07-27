package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.config.SecurityConfig;
import com.taskmanager.taskflow.exception.EmailAlreadyExistsException;
import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User registerUser(User user){
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException("Já existe um usuário cadastrado com esse e-mail");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Boolean loginTest(String storedHash, String passwordUser){
        System.out.println(storedHash);
        System.out.println(passwordUser);
        return passwordEncoder.matches(passwordUser, storedHash);
    }
}
