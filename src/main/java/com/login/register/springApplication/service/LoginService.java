package com.login.register.springApplication.service;


import com.login.register.springApplication.dto.LoginDto;
import com.login.register.springApplication.exception.UserNotFoundException;
import com.login.register.springApplication.model.LoginEntity;
import com.login.register.springApplication.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

@Slf4j
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);


    public void addNewUser(LoginDto loginDto) {
        if (loginRepository.existsByUsername(loginDto.username())) {
            throw new UserNotFoundException("Bu Kullanıcı Adı Bir Başkası Tarafından Kullanılıyor");
        }
        logger.info("Kullanıcı ekleniyor: {}", loginDto.name());

        LoginEntity loginEntity = LoginDto.convert(loginDto);
        loginRepository.save(loginEntity);
        logger.info("kullanıcı başarıyla eklendi: {}","Kulanıcı Adı :  "+ loginEntity.getUsername()+"  İsim Soyisim : "+loginEntity.getName()+" "+loginEntity.getSurname());
//        LoginEntity user = new LoginEntity(userName, password, name, surname, mail);
//        return loginRepository.save(user);
    }


    public List<LoginEntity> getAllUsers() {
        return loginRepository.findAll();
    }


    public Optional<LoginEntity> getUserByUserName(String userName) {
        return loginRepository.findByUsername(userName);
    }


    public Object login(String userName, String password) {
        if (!loginRepository.existsByUsername(userName)) {
            throw new UserNotFoundException("Böyle Bir Kullanıcı Mevcut Değil");
        }
        return loginRepository.findByUsernameAndPassword(userName, password)
                .orElseThrow(() -> new UserNotFoundException("Kullanıcı Adı Veya Şifre Hatalı"));
    }
}