package org.example.frigoservice.repository;

import org.example.frigoservice.entity.FrigoAliment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FrigoAlimentRepository extends CrudRepository<FrigoAliment, Integer> {
    Optional<FrigoAliment> findByFrigoIdAndIdAliment(int idFrigo, int idAliment);

    List<FrigoAliment> findAllByFrigoId(int idFrigo);
}
