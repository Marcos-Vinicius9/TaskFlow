package com.taskmanager.taskflow.service;

import com.taskmanager.taskflow.exception.EmailAlreadyExistsException;
import com.taskmanager.taskflow.model.User;
import com.taskmanager.taskflow.model.UserActivation;
import com.taskmanager.taskflow.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserActivationService userActivationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers(){
        return this.userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }

    public User registerUser(User user) throws MessagingException {
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            throw new EmailAlreadyExistsException("Já existe um usuário cadastrado com esse e-mail");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UUID tokenConfirmation = generateTokenConfirmation();
        UserActivation userActivation = new UserActivation(tokenConfirmation, userRepository.save(user).getId());
        userActivationService.addUserActivationToken(userActivation);
        System.out.println(tokenConfirmation.toString());
        emailService.sendConfirmationMessage(user, tokenConfirmation.toString());
        return user;
    }

    public Boolean loginTest(String storedHash, String passwordUser){
        return passwordEncoder.matches(passwordUser, storedHash);
    }

    public Boolean confirmationAccount(String token){
        return this.userActivationService.confirmActivationAccount(token);
    }
    public UUID generateTokenConfirmation (){
        return UUID.randomUUID();
    }

}
