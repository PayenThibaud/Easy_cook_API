package org.example.frigoservice.repository;

import org.example.frigoservice.entity.Frigo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrigoRepository extends CrudRepository<Frigo, Integer> {
}
