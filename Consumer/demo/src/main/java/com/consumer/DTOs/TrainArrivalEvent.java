package com.consumer.DTOs;

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
    private Integer stationId;
    private int order1;
    private boolean red;
    private boolean blue;
    private boolean green;
}