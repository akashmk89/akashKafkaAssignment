package com.producer.utils;

import com.producer.models.Station;
import com.producer.service.StationsService;
import com.producer.service.WeatherDataProducer;
import com.producer.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class Schedular {

    @Autowired
    StationsService stationsService;

    @Autowired
    CsvReader csvReader;

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherDataProducer weatherDataProducer;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void updateStationRecordsInDatabase() throws IOException, ParseException, Exception {
        log.info("CSV to Database scheduled function ran at" + LocalDateTime.now());
        List<Station> stations = csvReader.readCSVAndGetRecords();
        stationsService.addRecordsToDataBase(stations);

    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void populateDatabaseRecordsToKTable() throws IOException, ParseException, Exception {
        log.info("populating stations to kafka" + LocalDateTime.now());
        List<Station> stations = stationsService.getAllStation();


    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void getWeatherInformation() throws IOException{
        log.info("calling weather information");
       float temperature = weatherService.getTemperature("Bangalore");
       weatherDataProducer.sendWeatherEvent(temperature);

    }
}
