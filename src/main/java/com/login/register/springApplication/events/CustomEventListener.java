package com.login.register.springApplication.events;

import com.login.register.springApplication.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener {

    private final EmailService emailService;

    public CustomEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Event received for user: " + event.getUsername());
        emailService.sendWelcomeEmail(event.getEmail(), event.getUsername());
    }
}
