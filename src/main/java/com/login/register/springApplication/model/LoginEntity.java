package com.login.register.springApplication.model;
import lombok.*;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection="users")
@Data
@Builder
@Getter
@Setter
public class LoginEntity {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private String mail;

    public LoginEntity(String username, String password, String name, String surname, String mail) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }


  }
