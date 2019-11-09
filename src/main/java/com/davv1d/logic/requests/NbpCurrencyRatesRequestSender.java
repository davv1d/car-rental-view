package com.davv1d.logic.requests;

import com.davv1d.logic.domain.nbp.Rates;
import com.davv1d.logic.requests.url.NbpCurrencyRatesUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class NbpCurrencyRatesRequestSender extends Request {
    @Autowired
    private NbpCurrencyRatesUrl nbpCurrencyRatesUrl;

    public ResponseEntity<Rates[]> getCurrencyRates() {
        URI currencyRates = nbpCurrencyRatesUrl.getCurrencyRates();
        return sendRequestWithoutAuthorize(currencyRates, HttpMethod.GET, Rates[].class, null);
    }
}
