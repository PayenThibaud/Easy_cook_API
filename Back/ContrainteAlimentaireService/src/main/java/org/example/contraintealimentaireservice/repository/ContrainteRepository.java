package org.example.contraintealimentaireservice.repository;

import org.example.contraintealimentaireservice.entity.ContrainteAlimentaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContrainteRepository  extends CrudRepository<ContrainteAlimentaire, Integer> {
}
