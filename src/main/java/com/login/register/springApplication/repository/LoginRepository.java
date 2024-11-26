package com.login.register.springApplication.repository;

import com.login.register.springApplication.model.LoginEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoginRepository extends MongoRepository<LoginEntity, String> {

    boolean existsByUsername(String username);

    Optional<LoginEntity> findByUsername(String username);

    Optional<LoginEntity> findByUsernameAndPassword(String username, String password);
}
