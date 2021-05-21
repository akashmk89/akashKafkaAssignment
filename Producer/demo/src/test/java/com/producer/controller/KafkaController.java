//package com.producer.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.producer.DTOs.TrainArrivalEvent;
//import com.producer.DTOs.TurnstileEvent;
//import com.producer.service.TrainArrivalEventProducer;
//import com.producer.service.TurnstileEventProducer;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@WebMvcTest(KafkaController.class)
//public class KafkaController {
//    @Autowired
//    MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @MockBean
//    TrainArrivalEventProducer trainArrivalEventProducer;
//
//    @MockBean
//    TurnstileEventProducer turnstileEventProducer;
//
//    @Test
//    void testPostTurnstileEventEvent() throws Exception {
//        TurnstileEvent turnstileEvent = TurnstileEvent.builder()
//                .stationId(1)
//                .stationName("delhi")
//                .line("red")
//                .build();
//
//        String json = objectMapper.writeValueAsString(turnstileEvent);
//        when(turnstileEventProducer.sendTurnstileEvent(isA(TurnstileEvent.class))).thenReturn(null);
//
//        //expect
//        mockMvc.perform(post("/kafka/turnstile-event")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void testPostTrainArrivalEventEvent() throws Exception {
//         TrainArrivalEvent trainArrivalEvent = TrainArrivalEvent.builder()
//                .stationId(1)
//                .directionId(1)
//                .stopName("delhi")
//                .stationDescription("delhi-central")
//                .stationId(9999)
//                .order(2)
//                .red(true)
//                .green(false)
//                .blue(false)
//                .build();
//
//        String json = objectMapper.writeValueAsString(trainArrivalEvent);
//        when(trainArrivalEventProducer.sendArrivalEvent(isA(TrainArrivalEvent.class))).thenReturn(null);
//
//        //expect
//        mockMvc.perform(post("/kafka/train-arrival-event")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
