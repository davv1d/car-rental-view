package com.davv1d.logic.requests.url;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class EndpointsCarRental {
    final String rootApiUrl = "http://localhost:8080";

    final String availabilityCarUrl = rootApiUrl + "/v1/cars/availability";
    final String carsUrl = rootApiUrl + "/v1/cars/getCars";
    final String carByVinUrl = rootApiUrl + "/v1/cars/getCar/";
    final String createCarUrl = rootApiUrl + "/v1/cars/createCars";
    final String deleteCarUrl = rootApiUrl + "/v1/cars/delete/";

    final String conditionForecastUrl = rootApiUrl + "/v1/condition/forecast/";
    final String conditionWeatherUrl = rootApiUrl + "/v1/condition/weather/";

    final String signInUrl = rootApiUrl + "/api/auth/signin";
    final String singUpUrl = rootApiUrl + "/api/auth//signup";

    final String ratesUrl = rootApiUrl + "/v1/rates";

    final String rentalUrl = rootApiUrl + "/v1/rental/";
    final String addRentalUrl = rootApiUrl + "/v1/rental/create";
    final String loggedUserRentalUrl = rootApiUrl + "/v1/rental/user";

    final String userUrl = rootApiUrl + "/v1/users/";
    final String changeEmail = rootApiUrl + "/v1/users/email";
    final String loggedUserUrl = rootApiUrl + "/v1/loggedUser";

    URI createUriWithoutParameters(String path) {
        return UriComponentsBuilder.fromHttpUrl(path).build().encode().toUri();
    }

    URI createUriWithParams(String path, Map<String, String> params) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(path);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
        }
        return uriComponentsBuilder.build().encode().toUri();
    }
}
