package com.login.register.springApplication.repository;

import com.login.register.springApplication.model.LoginEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LoginRepository extends CassandraRepository<LoginEntity, String>  {

    @AllowFiltering
    Optional<LoginEntity>findByUsernameAndPassword(String userName,String password);


    @AllowFiltering
    Optional<LoginEntity> findByUsername(String userName);


     boolean existsByUsername(String userName);




}
