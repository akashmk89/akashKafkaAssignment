package com.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Slf4j
public class StationKTableProcessor {
    public String topic = "org.station.stations";

    public void addStationToKafka(){
    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "HelloStreams");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    StreamsBuilder streamsBuilder = new StreamsBuilder();
    KTable<Integer,String> kTable = streamsBuilder.table(topic);
    kTable.toStream().foreach((k,v) -> System.out.println("key=" + k + " value= "+v));

    KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
    streams.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        log.info("application added successfully");
        streams.close();
        }));
}
}
