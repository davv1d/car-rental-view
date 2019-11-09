package com.davv1d.logic.requests.url;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WeatherUrl extends EndpointsCarRental {
    public URI getConditionForecastUri(String city) {
        return getUri(conditionForecastUrl, city);
    }

    public URI getConditionWeatherUri(String city) {
        return getUri(conditionWeatherUrl, city);
    }

    private URI getUri(String path, String city) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("city", city);
        return createUriWithParams(path, params);
    }

}
