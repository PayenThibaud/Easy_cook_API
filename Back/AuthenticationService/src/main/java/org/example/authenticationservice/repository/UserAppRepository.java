package org.example.authenticationservice.repository;

import org.example.authenticationservice.entity.UserApp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepository extends CrudRepository<UserApp, Integer> {
    Optional<UserApp> findByEmail(String email);
}

