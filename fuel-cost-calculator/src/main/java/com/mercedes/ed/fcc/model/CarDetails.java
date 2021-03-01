package com.mercedes.ed.fcc.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("fcc_car_details")
@Data
public class CarDetails extends PersistableMongoEntity {

    /**
     * List of Fuel costs calculated for each Car.
     */
    private List<FuelCost> fuelCosts = new ArrayList<>();
}
