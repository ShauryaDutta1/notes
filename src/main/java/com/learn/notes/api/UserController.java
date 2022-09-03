package com.learn.notes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.GenericResponseMulti;
import com.learn.notes.config.UserRequest;
import com.learn.notes.model.User;
import com.learn.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping(value = "/")
    private GenericResponse<User> addUser(@RequestBody UserRequest request){
        System.out.println("Request Data: " + request);
        User existing = userService.findExistingUserByEmail(request.getInstitutionalEmail());
        if (existing!=null)
        {
            System.out.println("User Already Existing");
            return new GenericResponse<>("Error", "User Already Exists", null);
        }
        User user = mapper.convertValue(request, User.class);
        user.setDeleted(false);
        User savedUser = userService.addUser(user);
        GenericResponse response = new GenericResponse("Success","User Saved", savedUser);
        System.out.println("Generic: " + response);
        return response;
    }

    @GetMapping(value = "/")
    private GenericResponseMulti<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        GenericResponseMulti response = new GenericResponseMulti("Success","Users Fetched", users);
        System.out.println("Generic Response: " + response);
        return response;
    }

    @GetMapping(value = "/{email}")
    private GenericResponse<User> getUserByEmailId(){
        List<User> users = userService.getAllUsers();
        GenericResponse response = new GenericResponse("Success","Users Fetched", users);
        System.out.println("Generic Response: " + response);
        return response;
    }

}
