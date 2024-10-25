package org.example.frigoservice.repository;

import org.example.frigoservice.entity.FrigoAliment;
import org.springframework.data.repository.CrudRepository;

public interface FrigoAlimentRepository extends CrudRepository<FrigoAliment, Integer> {
}
