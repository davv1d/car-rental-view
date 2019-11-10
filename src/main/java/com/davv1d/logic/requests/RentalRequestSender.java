package com.davv1d.logic.requests;

import com.davv1d.logic.domain.rental.RentalDto;
import com.davv1d.logic.domain.rental.SaveRentalDto;
import com.davv1d.logic.requests.url.RentalUrlCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class RentalRequestSender extends Request{
    @Autowired
    private RentalUrlCreator rentalUrlCreator;

    public ResponseEntity<RentalDto[]> getAllRentals() {
        URI allRentalsUri = rentalUrlCreator.getRentalWithoutParameterUri();
        return sendRequestWithAuthorize(allRentalsUri, HttpMethod.GET, RentalDto[].class, null);
    }

    public ResponseEntity<Void> createRental(SaveRentalDto saveRentalDto) {
        URI rentalUri = rentalUrlCreator.createRentalUri();
        return sendRequestWithAuthorize(rentalUri, HttpMethod.POST, Void.class, saveRentalDto);
    }

    public ResponseEntity<Void> deleteRental(String id) {
        URI rentalUriWthOneParameter = rentalUrlCreator.getRentalUriWthOneParameter("id", id);
        return sendRequestWithAuthorize(rentalUriWthOneParameter, HttpMethod.DELETE, Void.class, null);
    }

    public ResponseEntity<RentalDto[]> getRentalByUsername(String username) {
        URI rentalUriWthOneParameter = rentalUrlCreator.getRentalUriWthOneParameter("username",username);
        return sendRequestWithAuthorize(rentalUriWthOneParameter, HttpMethod.GET, RentalDto[].class, null);
    }

    public ResponseEntity<RentalDto[]> getRentalByVin(String vin) {
        URI rentalUriWthOneParameter = rentalUrlCreator.getRentalUriWthOneParameter("vin",vin);
        return sendRequestWithAuthorize(rentalUriWthOneParameter, HttpMethod.GET, RentalDto[].class, null);
    }

    public ResponseEntity<RentalDto[]> getLoggedUserRentals() {
        URI loggedUserRentals = rentalUrlCreator.getLoggedUserRentals();
        return sendRequestWithAuthorize(loggedUserRentals, HttpMethod.GET, RentalDto[].class, null);
    }

}
