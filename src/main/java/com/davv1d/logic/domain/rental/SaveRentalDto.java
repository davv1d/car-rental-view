package com.davv1d.logic.domain.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveRentalDto {
    private long id;
    private String username;
    private String vin;
    private LocalDateTime dateOfRent;
    private LocalDateTime dateOfReturn;

    public SaveRentalDto(String username, String vin, LocalDateTime dateOfRent, LocalDateTime dateOfReturn) {
        this.username = username;
        this.vin = vin;
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfReturn;
    }
}
