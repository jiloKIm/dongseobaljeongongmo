package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(String fullname, String email, String password) {
        User user = new User(fullname, email, password);
        userRepository.save(user);
        return user;
    }

    public User confirm(String password) {
        User user = userRepository.findBypassword(password);
        return user;
    }

    public User find(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }
}
