package com.mercedes.ed.fcc.service;

import com.mercedes.ed.fcc.dto.CarDetailsDTO;

public interface ConsumerService {

    /**
     * Saves car details and fuel cost after calculations
     * @param fuelCost
     */
    void save(CarDetailsDTO fuelCost);
}
