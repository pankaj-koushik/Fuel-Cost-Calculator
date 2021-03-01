package com.mercedes.ed.fcc.service.impl;

import com.mercedes.ed.fcc.dto.CarDetailsDTO;
import com.mercedes.ed.fcc.model.CarDetails;
import com.mercedes.ed.fcc.model.FuelCost;
import com.mercedes.ed.fcc.repo.ConsumerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes.ed.fcc.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    final ConsumerRepo consumerRepo;

    /**
     * Object mapper for data transformations
     */
    final ObjectMapper objectMapper;

    public ConsumerServiceImpl(ConsumerRepo consumerRepo, ObjectMapper objectMapper) {
        this.consumerRepo = consumerRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public void save(CarDetailsDTO carDetailsDTO) {

        Optional<CarDetails> carDetailsOp = consumerRepo.findById(carDetailsDTO.getCarId());
        if(carDetailsOp.isPresent())
        {
            log.info("Car details found for the given id {}",carDetailsDTO.getCarId());
            CarDetails carDetails = carDetailsOp.get();
            carDetails.getFuelCosts().add(objectMapper.convertValue(carDetailsDTO.getFuelCost(), FuelCost.class));
            consumerRepo.save(carDetails);
        }
        else {
            log.info(" No Car details found for the given id {}",carDetailsDTO.getCarId());
            CarDetails carDetails = new CarDetails();
            carDetails.setEntityId(carDetailsDTO.getCarId());
            List<FuelCost>fuelCosts = new ArrayList<>();
            fuelCosts.add(objectMapper.convertValue(carDetailsDTO.getFuelCost(), FuelCost.class));
            carDetails.setFuelCosts(fuelCosts);
            carDetails.setEntityPersistDT(new Date());
            log.info(" Saved Car details with  id {}",carDetailsDTO.getCarId());
            consumerRepo.save(carDetails);
        }


    }
}
