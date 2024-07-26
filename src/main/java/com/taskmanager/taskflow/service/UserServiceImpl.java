package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public User registerUser(User user){
        return this.userRepository.save(user);
    }

}
