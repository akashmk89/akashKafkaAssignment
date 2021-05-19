package com.producer.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainArrivalEvent {
    private int stopId;
    private int directionId;
    private String stopName;
    private String stationDescription;
    private int stationId;
    private int order;
    private boolean red;
    private boolean blue;
    private boolean green;
}
