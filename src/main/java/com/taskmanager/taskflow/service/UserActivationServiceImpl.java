package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.model.UserActivation;
import com.taskmanager.taskflow.repository.UserActivationRepository;
import com.taskmanager.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserActivationServiceImpl implements UserActivationService{

    @Autowired
    private UserActivationRepository userActivationRepository;

    @Autowired
    private UserRepository userRepository;

    public void addUserActivationToken(UserActivation user){
        userActivationRepository.save(user);
    }

    @Override
    public Boolean confirmActivationAccount(String token) {
        UserActivation user = userActivationRepository.userActivation(UUID.fromString(token));
        Optional<User> userActive = userRepository.findById(user.getUserId());
       if(userActive.isPresent()){
           return active(userActive.get().getEmail());

       }
       return false;
    }

    public Boolean active (String email){
        User userFound = userRepository.findUserByEmail(email);
        if(userFound !=null && !userFound.isActive()){
            userFound.setActive(true);
            userRepository.save(userFound);
            return true;
        }
        return false;
    }

}
