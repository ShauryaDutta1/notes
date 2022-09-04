package com.learn.notes.api;

import com.learn.notes.config.FileRequest;
import com.learn.notes.config.GenericResponse;
import com.learn.notes.config.GenericResponseMulti;
import com.learn.notes.model.Email;
import com.learn.notes.model.File;
import com.learn.notes.model.User;
import com.learn.notes.service.EmailService;
import com.learn.notes.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    private GenericResponse<Boolean> sendOtp(@RequestParam("email") String email,
                                             @RequestParam("name") String name){
        GenericResponse<Boolean> response = emailService.sendOtp(email, name);
        return response;
    }

    @GetMapping("/verify")
    private GenericResponse<Boolean> verifyOtp(@RequestParam("email") String email,
                                               @RequestParam("otp") String otp){
        GenericResponse<Boolean> response = emailService.verifyOtp(email,otp);
        return response;
    }

}
