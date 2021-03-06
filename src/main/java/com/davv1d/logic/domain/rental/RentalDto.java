package com.davv1d.logic.domain.rental;

import com.davv1d.logic.domain.car.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentalDto {
    private long id;
    private String username;
    private Car car;
    private LocalDateTime dateOfRent;
    private LocalDateTime dateOfReturn;
}
