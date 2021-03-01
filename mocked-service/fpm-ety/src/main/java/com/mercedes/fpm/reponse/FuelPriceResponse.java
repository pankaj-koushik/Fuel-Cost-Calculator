package com.mercedes.fpm.reponse;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FuelPriceResponse {
    private String message;
    private List<FuelPriceDTO> fuelPrices = new ArrayList<>();
}
