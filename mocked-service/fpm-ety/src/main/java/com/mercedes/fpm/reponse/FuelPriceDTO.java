package com.mercedes.fpm.reponse;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FuelPriceDTO {
    private String district;
    private List<ProductDTO> products = new ArrayList<>();

}
