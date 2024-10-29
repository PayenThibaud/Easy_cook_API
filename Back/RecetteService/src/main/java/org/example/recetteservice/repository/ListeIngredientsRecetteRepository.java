package org.example.recetteservice.repository;
import org.example.recetteservice.entity.ListeIngredientsRecette;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListeIngredientsRecetteRepository extends CrudRepository<ListeIngredientsRecette, Integer> {
}
