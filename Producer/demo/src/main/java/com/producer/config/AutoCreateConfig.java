package com.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {
    @Bean
    public NewTopic arrivalEvents(){
        return TopicBuilder.name("org.station.arrivals")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic turnstileEvents(){
        return TopicBuilder.name("org.station.turnstiles")
                .partitions(1)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic stations(){
        return TopicBuilder.name("org.station.stations")
                .partitions(1)
                .replicas(1)
                .build();
    }
    public NewTopic weather(){
        return TopicBuilder.name("org.station.weather")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
