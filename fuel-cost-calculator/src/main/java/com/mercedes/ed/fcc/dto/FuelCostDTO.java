package com.mercedes.ed.fcc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FuelCostDTO {

    /**
     * City of which fuel price will be used to calculate cost
     */
    private String city;
    /**
     * Fuel consumption cost
     */
    private Double cost;
    /**
     * Fuel cost per Liter
     */
    private Double fuelPrice;
    /**
     * Timestamp
     */
    private Date createdDate;
}
