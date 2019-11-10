package com.davv1d.logic.mapper;

import com.davv1d.logic.domain.rental.Rental;
import com.davv1d.logic.domain.rental.RentalDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    public Rental mapToRental(final RentalDto rentalDto) {
        return new Rental(
                String.valueOf(rentalDto.getId()),
                rentalDto.getUsername(),
                rentalDto.getCar().getVinNumber(),
                rentalDto.getCar().getBrand(),
                rentalDto.getCar().getModel(),
                rentalDto.getDateOfRent().toString(),
                rentalDto.getDateOfReturn().toString());
    }

    public List<Rental> mapToRentalList(final List<RentalDto> rentalsDto) {
        return rentalsDto.stream()
                .map(this::mapToRental)
                .collect(Collectors.toList());
    }
}
