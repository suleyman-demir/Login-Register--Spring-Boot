package com.login.register.springApplication.dto;

import com.login.register.springApplication.model.LoginEntity;

public record LoginDto (
        String username,
        String password,
        String mail,
        String name,
        String surname
){

    public static LoginDto convert(LoginEntity from){
        return new LoginDto(from.getUsername(), from.getPassword(), from.getMail(), from.getName(), from.getSurname());
    }

    public static LoginEntity convert(LoginDto from){
        return new LoginEntity(from.username(), from.password(), from.name(), from.surname(), from.mail());
    }


    private void setMail(String mail) {
    }
    private void setName(String name) {
    }
    private void setSurname(String surname) {
    }
    private void setId(String id) {
    }

    private void setPassword(String password) {
    }

    private void setUsername(String username) {
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String mail() {
        return mail;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String surname() {
        return surname;
    }

}

