package com.davv1d.logic.domain.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveRentalDto {
    private String vin;
    private LocalDateTime dateOfRent;
    private LocalDateTime dateOfReturn;
}
