package com.davv1d.logic.requests.url;

import com.davv1d.logic.domain.car.Car;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CarUrl extends EndpointsCarRental {
    public URI getAvailabilityCarUrl(String dateOfRent, String dateOfReturn) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("dateOfRent", dateOfRent);
        params.put("dateOfReturn", dateOfReturn);
        return createUriWithParams(availabilityCarUrl, params);
    }

    public URI getCarsUri() {
        return createUriWithoutParameters(carsUrl);
    }

    public URI getCarByVinUri(String vinNumber) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("vin", vinNumber);
        return createUriWithParams(carByVinUrl, params);
    }

    public URI getCreateCarUri() {
        return createUriWithoutParameters(createCarUrl);
    }

    public URI getDeleteCarUrl(String vinNumber) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("vin", vinNumber);
        return createUriWithParams(deleteCarUrl, params);
    }

    public URI changeAvailabilityCarUrl() {
        return createUriWithoutParameters(availabilityCarUrl);
    }
}
