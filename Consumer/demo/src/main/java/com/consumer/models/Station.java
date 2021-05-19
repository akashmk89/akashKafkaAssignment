package com.consumer.models;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Station {
    @Id
    @Column(name ="stop_id")
    private int stopId;
    @Column(name ="direction_id")
    private String directionId;
    @Column(name ="stop_name")
    private String stopName;
    @Column(name ="station_name")
    private String stationName;
    @Column(name ="station_description")
    private String stationDescription;
    @Column(name ="station_id")
    private int stationId;
    @Column(name ="order")
    private int order;
    @Column(name ="red")
    private Boolean red;
    @Column(name ="blue")
    private Boolean blue;
    @Column(name ="green")
    private Boolean green;
}
