package org.example.authenticationservice.repository;

import org.example.authenticationservice.entity.UtilisateurApp;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurAppRepository extends CrudRepository<UtilisateurApp, Integer> {
    Optional<UtilisateurApp> findByEmail(String email);
}
