package com.login.register.springApplication.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
@Builder
@AllArgsConstructor
@Getter
public class UserRegisteredEvent {
    private final String username;
    private final String email;


}
