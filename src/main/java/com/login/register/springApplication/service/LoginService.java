package com.login.register.springApplication.service;

import com.login.register.springApplication.dto.LoginDto;
import com.login.register.springApplication.events.UserRegisteredEvent;
import com.login.register.springApplication.exception.UserNotFoundException;
import com.login.register.springApplication.model.LoginEntity;
import com.login.register.springApplication.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {


    private final LoginRepository loginRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    private final ApplicationEventPublisher eventPublisher;




    public void addNewUser(LoginDto loginDto) {
        if (loginRepository.existsByUsername(loginDto.username())) {
            throw new UserNotFoundException("Bu Kullanıcı Adı Bir Başkası Tarafından Kullanılıyor");
        }
        logger.info("Kullanıcı ekleniyor: {}", loginDto.name());

        LoginEntity loginEntity = LoginDto.convert(loginDto);
        loginRepository.save(loginEntity);
        logger.info("Kullanıcı başarıyla eklendi: {}",
                "Kullanıcı Adı: " + loginEntity.getUsername() +
                        " İsim Soyisim: " + loginEntity.getName() + " " + loginEntity.getSurname());

        // Event yayınlama
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .username(loginDto.username())
                .email(loginDto.mail())
                .build();
        eventPublisher.publishEvent(event);
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
