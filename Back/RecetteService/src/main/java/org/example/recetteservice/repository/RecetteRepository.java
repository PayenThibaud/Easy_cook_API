package org.example.recetteservice.repository;

import org.example.recetteservice.entity.Recette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Recette, Long> {
    List<Recette> findByIngredientsIn(List<Ingredient> ingredients);
    List<Recette> findByNomContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nom, String description);
}