package com.davv1d.logic.domain.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rental {
    private String id;
    private String username;
    private String vinNumber;
    private String brand;
    private String model;
    private String dateOfRent;
    private String dateOfReturn;
}
