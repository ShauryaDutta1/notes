package com.learn.notes.service;

import com.learn.notes.model.User;
import com.learn.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        User saved = userRepository.save(user);
        System.out.println("User Saved: " + saved);
        return saved;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println("Users: " + users);
        return users;
    }

    public User findExistingUserByEmail(String institutionalEmail) {
        return userRepository.findUserByEmail(institutionalEmail);
    }
}
