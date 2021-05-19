package com.producer.service;

import com.producer.models.Station;
import com.producer.repository.StationRepository;
import com.producer.utils.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StationsService {
    @Autowired
    StationRepository stationRepository;

    public List<Station> getAllStation(){
        return  (List<Station>) stationRepository.findAll();
    }

    public boolean addStationIfNotExistToDatabase(Station station) throws Exception{
        if(this.CanAddRecord(station.getStationId())) {
            stationRepository.save(station);
            return true;
        }
        return false ;
    }
    public boolean CanAddRecord(Integer stationId) throws Exception{
        Optional<Station> existingNetflixShow = stationRepository.findById(stationId);
        if(existingNetflixShow.isPresent()){
            return false;
        }
        return true;
    }
    @Transactional
    public void addRecordsToDataBase(List<Station> stations) throws Exception{
        for (Station station:stations) {
            this.addStationIfNotExistToDatabase(station);
        }
    }
}
