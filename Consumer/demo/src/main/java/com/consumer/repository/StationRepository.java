package com.consumer.repository;

import com.consumer.models.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station,Integer> {
}
