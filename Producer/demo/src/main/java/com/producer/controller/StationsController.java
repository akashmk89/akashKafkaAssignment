package com.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.DTOs.TrainArrivalEvent;
import com.producer.DTOs.TurnstileEvent;
import com.producer.models.Station;
import com.producer.service.StationKTableProcessor;
import com.producer.service.StationsEventProducer;
import com.producer.service.TrainArrivalEventProducer;
import com.producer.service.TurnstileEventProducer;
import com.producer.utils.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/station")
@CrossOrigin(origins = "127.0.0.1")
public class StationsController {
    @Autowired
    CsvReader csvReader;

    @Autowired
    TrainArrivalEventProducer trainArrivalEventProducer;

    @Autowired
    TurnstileEventProducer turnstileEventProducer;

    @Autowired
    StationKTableProcessor stationKTableProcessor;

    @Autowired
    StationsEventProducer stationEventProducer;

    @GetMapping("/test-k-table")
    public void readCSVandGetStations() throws IOException, ParseException {
        stationKTableProcessor.addStationToKafka();
    }

    @PostMapping("train-arrival-event")
    public ResponseEntity<TrainArrivalEvent> produceTrainArrivalEvent(@RequestBody  TrainArrivalEvent trainArrivalEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        trainArrivalEventProducer.sendArrivalEvent(trainArrivalEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainArrivalEvent);
    }

    @PostMapping("turnstile-event")
    public ResponseEntity<TurnstileEvent> produceTurnstileEvent(@RequestBody  TurnstileEvent turnstileEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        turnstileEventProducer.sendTurnstileEvent(turnstileEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnstileEvent);
    }

    @PostMapping("create-station")
    public void readCSVandGetStations(@RequestBody Station station) throws IOException, ParseException {
        stationEventProducer.sendStationsEvent(station);
    }
}
