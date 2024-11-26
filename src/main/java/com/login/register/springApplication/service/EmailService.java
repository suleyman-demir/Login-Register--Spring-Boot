package com.login.register.springApplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;


    public void sendWelcomeEmail(String toEmail, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Our Platform!");
        message.setText("Hello " + username + ",\n\nWelcome to our platform! We're excited to have you onboard.\n\nBest regards,\nYour Team");

        mailSender.send(message);
        System.out.println("Email sent to: " + toEmail);
    }
}
