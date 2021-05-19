package com.consumer.service;

import com.consumer.DTOs.TrainArrivalEvent;
import com.consumer.models.Station;
import com.consumer.models.TransformedStation;
import com.consumer.repository.StationRepository;
import com.consumer.repository.TransformedStationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Optional;

@Service
@Slf4j
public class TrainArrivalEventService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<Integer,String> kafkaTemplate;

    @Autowired
    private TransformedStationRepository transformedStationRepository;

    @Autowired
    private StationRepository stationRepository;

    public void processTrainArrivalEvent(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        TrainArrivalEvent trainArrivalEvent = objectMapper.readValue(consumerRecord.value(), TrainArrivalEvent.class);
        log.info("libraryEvent : {} ", trainArrivalEvent);

        if(trainArrivalEvent.getStationId()!=null && trainArrivalEvent.getStationId()==000){
            throw new RecoverableDataAccessException("Temporary Network Issue");
        }
         validate(trainArrivalEvent);
        save(trainArrivalEvent);
    }

    private void validate(TrainArrivalEvent trainArrivalEvent) {
        if(trainArrivalEvent.getStationId()==null){
            throw new IllegalArgumentException("Station Id is missing");
        }

        Optional<Station> station = stationRepository.findById(trainArrivalEvent.getStationId());
        if(!station.isPresent()){
            throw new IllegalArgumentException("Not a valid station Event");
        }

    }

    private void save(TrainArrivalEvent trainArrivalEventEvent) {
        TransformedStation transformedStation = getTransformedStation(stationRepository.findById(trainArrivalEventEvent.getStationId()));
        transformedStationRepository.save(transformedStation);
        log.info("successfully saved transformed station");
    }

    private TransformedStation getTransformedStation(Optional<Station> station){
       Station stationObject= station.get();
        TransformedStation transformedStation = new TransformedStation();
        transformedStation.setStationId(stationObject.getStationId());
        transformedStation.setStationName(stationObject.getStationName());
        transformedStation.setOrder(stationObject.getOrder());
        transformedStation.setLine(getLineOfStation(stationObject));
        return  transformedStation;
    }

    public String getLineOfStation(Station station){
        if(station.getBlue()== true){
            return "Blue";
        }else if(station.getRed() == true){
            return "Red";
        }else{
            return "Green";
        }
    }
//
    public void handleRecovery(ConsumerRecord<Integer,String> record){

        Integer key = record.key();
        String message = record.value();

        ListenableFuture<SendResult<Integer,String>> listenableFuture = kafkaTemplate.sendDefault(key, message);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                handleFailure(key, message, ex);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                handleSuccess(key, message, result);
            }
        });
    }
//
    private void handleFailure(Integer key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
        try {
            throw ex;
        } catch (Throwable throwable) {
            log.error("Error in OnFailure: {}", throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }


}
