package com.login.register.springApplication.controller;

import com.login.register.springApplication.dto.LoginDto;
import com.login.register.springApplication.model.LoginEntity;
import com.login.register.springApplication.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/v1/api")
@Tag(name = "Login Register API " +
        "Suleyman Demir")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/getAllUsers")
    public ResponseEntity<List<LoginEntity>> getAllUsers(){
        List<LoginEntity> loginDtos = loginService.getAllUsers();
        if (loginDtos !=null && !loginDtos.isEmpty()) {
            return ResponseEntity.ok(loginDtos);

        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> addNewUser(@RequestBody LoginDto loginDto){
        loginService.addNewUser(loginDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<LoginEntity> getUserByUserName(@PathVariable String userName){
        return loginService.getUserByUserName(userName).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(loginService.login(loginDto.username(), loginDto.password()));
    }

}
