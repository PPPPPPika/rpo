package com.example.rpo.Services;

import com.example.rpo.Models.Enums.Role;
import com.example.rpo.Models.Enums.Status;
import com.example.rpo.Models.User;
import com.example.rpo.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("registrationService")
public class RegistrationService {
    private final UserRepository userRepository;
    private User user;

    @Autowired
    public RegistrationService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void initializationUser(User user){
        this.user = user;
    }

    private void encryptionPassword(){
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        this.user.setPassword(encryptedPassword);
    }

    private void additionUser(){
        this.user.setStatus(Status.ACTIVE);
        this.user.setRole(Role.USER);
    }

    private void createUser(){
        userRepository.save(this.user);
    }

    public void registrationUser(User user){
        initializationUser(user);
        encryptionPassword();
        additionUser();
        createUser();
    }

    public boolean checkForDuplicate(String name){
        return userRepository.existsUserByName(name);
    }
}
