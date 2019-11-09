package com.davv1d.logic.domain.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private String date;
    private String tempMax;
    private String temp;
    private String percentageCloudy;
    private String deg;
    private String description;
    private String humidity;
    private String pressure;
    private String speed;
    private String tempMin;
}
