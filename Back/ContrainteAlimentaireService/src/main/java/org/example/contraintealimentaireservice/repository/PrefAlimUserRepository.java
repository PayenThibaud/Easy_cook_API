package org.example.contraintealimentaireservice.repository;

import org.example.contraintealimentaireservice.entity.PrefAlimUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefAlimUserRepository extends CrudRepository<PrefAlimUser, Integer> {
    List<PrefAlimUser> findAllByUtilisateurId(int userId);
}
