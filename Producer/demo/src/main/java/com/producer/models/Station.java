package com.producer.models;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "station")
public class Station {
    @Id
    @Column(name ="stop_id")
    private int stopId;

    @Column(name ="direction_id")
    @NonNull
    private String directionId;

    @Column(name ="stop_name")
    @NonNull
    private String stopName;

    @Column(name ="station_name")
    @NonNull
    private String stationName;

    @Column(name ="station_description")
    @NonNull
    private String stationDescription;

    @Column(name ="station_id")
    @NonNull
    private int stationId;

    @Column(name ="order_1")
    private int order;

    @Column(name ="red")
    @NonNull
    private Boolean red;

    @Column(name ="blue")
    @NonNull
    private Boolean blue;

    @NonNull
    @Column(name ="green")
    private Boolean green;
}
