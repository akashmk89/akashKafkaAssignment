package com.consumer.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurnstileEvent {
private int stationId;
private String stationName;
private  String line;
}
