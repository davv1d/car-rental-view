package com.davv1d.logic.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    private String vinNumber;
    private String brand;
    private String model;
    private boolean availability;

    @Override
    public String toString() {
        return brand + " " + model + " " + vinNumber;
    }
}
