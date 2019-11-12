package com.davv1d.logic.requests;

import com.davv1d.logic.domain.car.Car;
import com.davv1d.logic.requests.url.CarUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CarRequestSender extends Request {
    @Autowired
    private CarUrl carUrl;

    public ResponseEntity<Car[]> getAvailabilityCars(String dateOfRent, String dateOfReturn) {
        URI availabilityCarUri = carUrl.getAvailabilityCarUrl(dateOfRent, dateOfReturn);
        return sendRequestWithAuthorize(availabilityCarUri, HttpMethod.GET, Car[].class, null);
    }

    public ResponseEntity<Car[]> getCars() {
        URI urlCars = carUrl.getCarsUri();
        return sendRequestWithAuthorize(urlCars, HttpMethod.GET, Car[].class, null);
    }

    public ResponseEntity<Car> getCarByVin(final String vinNumber) {
        URI carByVinUrl = carUrl.getCarByVinUri(vinNumber);
        return sendRequestWithAuthorize(carByVinUrl, HttpMethod.GET, Car.class, null);
    }

    public ResponseEntity<Void> createCar(final Car car) {
        URI createCarUri = carUrl.getCreateCarUri();
        return sendRequestWithAuthorize(createCarUri, HttpMethod.POST, Void.class, car);
    }

    public ResponseEntity<Void> deleteCarByVin(final String vinNumber) {
        URI deleteCarUrl = carUrl.getDeleteCarUrl(vinNumber);
        return sendRequestWithAuthorize(deleteCarUrl, HttpMethod.DELETE, Void.class, null);
    }

    public ResponseEntity<Void> changeAvailabilityCar(final String vinNumber) {
        URI availabilityCarUrl = carUrl.changeAvailabilityCarUrl();
        return sendRequestWithAuthorize(availabilityCarUrl, HttpMethod.PUT, Void.class, vinNumber);
    }
}
