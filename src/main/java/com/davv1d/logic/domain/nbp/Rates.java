package com.davv1d.logic.domain.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rates {
    private String code;
    private Double ask;
    private String currency;
    private Double bid;
}
