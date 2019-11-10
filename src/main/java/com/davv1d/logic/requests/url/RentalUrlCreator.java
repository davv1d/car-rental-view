package com.davv1d.logic.requests.url;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class RentalUrlCreator extends EndpointsCarRental{

    public URI getRentalWithoutParameterUri() {
        return createUriWithoutParameters(rentalUrl);
    }

    public URI getRentalUriWthOneParameter(String name, String value) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put(name, value);
        return createUriWithParams(rentalUrl, params);
    }

    public URI createRentalUri() {
        return createUriWithoutParameters(addRentalUrl);
    }

    public URI getLoggedUserRentals() {
        return createUriWithoutParameters(loggedUserRentalUrl);
    }
}
