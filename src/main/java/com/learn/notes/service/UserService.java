package com.learn.notes.service;

import com.learn.notes.config.GenericResponse;
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

    public User findExistingUserByEmailAndInstituteId(String institutionalEmail, String instituteId) {
        return userRepository.findUserByEmailAndInstitutionalId(institutionalEmail, instituteId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public GenericResponse<Boolean> login(String email, String password) {
        User userByEmail = userRepository.findUserByEmail(email);
        GenericResponse<Boolean> response = null;
        if (userByEmail==null){
            System.out.println("User Not found with email: " + email);
            response = new GenericResponse<>("Error", "User Not found with email: " + email, false);
        }
        if (userByEmail!=null && password!=null && userByEmail.getPassword().equalsIgnoreCase(password))
            response = new GenericResponse<>("Success", "User Login Successful", true);
        else
            response = new GenericResponse<>("Error", "Incorrect Password Entered", false);
        return response;
    }
}
