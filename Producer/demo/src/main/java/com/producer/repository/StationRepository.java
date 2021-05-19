package com.producer.repository;

import com.producer.models.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station, Integer> {
}
