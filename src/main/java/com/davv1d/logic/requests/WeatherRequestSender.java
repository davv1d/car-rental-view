package com.davv1d.logic.requests;

import com.davv1d.logic.domain.weather.Weather;
import com.davv1d.logic.requests.url.WeatherUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class WeatherRequestSender extends Request {
    @Autowired
    private WeatherUrl weatherUrl;

    public ResponseEntity<Weather> getConditionWeather(String city) {
        URI conditionWeatherUri = weatherUrl.getConditionWeatherUri(city);
        return sendRequestWithoutAuthorize(conditionWeatherUri, HttpMethod.GET, Weather.class, null);
    }

    public ResponseEntity<Weather[]> getConditionsForecast(String city) {
        URI conditionForecastUri = weatherUrl.getConditionForecastUri(city);
        return sendRequestWithoutAuthorize(conditionForecastUri, HttpMethod.GET, Weather[].class, null);
    }
}
