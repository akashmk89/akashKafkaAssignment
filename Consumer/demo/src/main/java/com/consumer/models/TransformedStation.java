package com.consumer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class TransformedStation {
    @Id
    @Column(name="station_id")
    public int stationId;
    @Column(name="station_name")
    public String stationName;
    public int order;
    public String line;

}
