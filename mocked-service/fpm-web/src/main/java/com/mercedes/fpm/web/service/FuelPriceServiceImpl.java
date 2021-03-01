package com.mercedes.fpm.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.fpm.reponse.FuelPriceDTO;
import com.mercedes.fpm.reponse.FuelPriceResponse;
import com.mercedes.fpm.web.repo.FuelPriceRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelPriceServiceImpl implements FuelPriceService {

    final
    FuelPriceRepo fuelPriceRepo;

    final
    ObjectMapper objectMapper;

    public FuelPriceServiceImpl(FuelPriceRepo fuelPriceRepo, ObjectMapper objectMapper) {
        this.fuelPriceRepo = fuelPriceRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public FuelPriceResponse findAllPrice() {

        FuelPriceResponse fuelPriceResponse = new FuelPriceResponse();

        List<FuelPriceDTO> fuelPriceDTOS = objectMapper.convertValue(fuelPriceRepo.findAll(), new TypeReference<List<FuelPriceDTO>>() {
        });
        fuelPriceResponse.setFuelPrices(fuelPriceDTOS);
        return fuelPriceResponse;

    }
}
