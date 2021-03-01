package com.mercedes.ed.fcc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data

public class FuelCost {


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
