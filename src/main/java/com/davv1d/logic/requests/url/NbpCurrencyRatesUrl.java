package com.davv1d.logic.requests.url;

import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class NbpCurrencyRatesUrl extends EndpointsCarRental {
    public URI getCurrencyRates() {
        return createUriWithoutParameters(ratesUrl);
    }
}
