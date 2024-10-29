package org.example.recetteservice.repository;

import org.example.recetteservice.entity.Recette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetteRepository extends CrudRepository<Recette, Integer> {
}