package org.example.contraintealimentaireservice.repository;

import org.example.contraintealimentaireservice.entity.TagRegime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRegimeRepository extends CrudRepository<TagRegime, Integer> {
    List<TagRegime> findByContrainteAlimentaireId(int id);
    List<TagRegime> findAllByRecetteId(int id);
}
