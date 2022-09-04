package com.learn.notes.service;

import com.learn.notes.config.GenericResponse;
import com.learn.notes.model.OtpDetails;
import com.learn.notes.repository.OtpDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private OtpDetailsRepository otpDetailsRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public GenericResponse<Boolean> sendOtp(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        String otp= new DecimalFormat("000000").format(new Random().nextInt(900000));
        System.out.println("OTP generated: "+otp);
        OtpDetails otpDetails = new OtpDetails();
        otpDetails.setOtp(otp);
        otpDetails.setEmail(email);
        otpDetails.setCreatedDateTime(LocalDateTime.now());
        otpDetailsRepository.save(otpDetails);
        System.out.println("OTP saved in repository");
        message.setFrom(from);
        message.setTo(email);
        message.setText("Dear " + name + " , your OTP for registration on Notes App is " + otp + " . Use this to verify " +
                " your institutional Email ID.");
        message.setSubject("Email Verification Using OTP");
        try {
            mailSender.send(message);
            System.out.println("Mail Sent");
        }catch (Exception e){
            System.out.println("Error Occured while sending Mail: " + e.getMessage());
        }
        return new GenericResponse<>("Success", "Email Sent Successfully", true);
    }

    public GenericResponse<Boolean> verifyOtp(String email, String otp) {
        String otpByEmail = otpDetailsRepository.findOtpByEmail(email);
        String message=null;
        if (otpByEmail==null || otpByEmail.isBlank() || otpByEmail.isEmpty())
        {
            message = "No OtpDetails Found for User with email: " + email;
            System.out.println(message);
            return new GenericResponse<>("Error", message, false);
        }
        else if (otpByEmail.equalsIgnoreCase(otp)){
            message = "OtpDetails Verified for User with email: " + email;
            System.out.println(message);
        }
        return new GenericResponse<>("Success", message, true);
    }
}
