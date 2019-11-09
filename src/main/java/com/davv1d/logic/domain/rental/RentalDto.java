package com.davv1d.logic.domain.rental;

import com.davv1d.logic.domain.car.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RentalDto {
    private long id;
    private String username;
    private Car car;
    private LocalDateTime dateOfRent;
    private LocalDateTime dateOfReturn;
}
