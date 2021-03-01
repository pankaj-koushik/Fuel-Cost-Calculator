package com.mercedes.fpm.web.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mercedes.fpm.web.model.CityList;
import com.mercedes.fpm.web.model.FuelPrice;
import com.mercedes.fpm.web.repo.CityListRepo;
import com.mercedes.fpm.web.repo.FuelPriceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Configuration
@Slf4j
public class AddData {


    final
    ObjectMapper objectMapper;

    final
    FuelPriceRepo fuelPriceRepo;

    final
    CityListRepo cityListRepo;


    final
    ResourceLoader resourceLoader;

    public AddData(ObjectMapper objectMapper, FuelPriceRepo fuelPriceRepo, CityListRepo cityListRepo, @Qualifier("webApplicationContext") ResourceLoader resourceLoader) {
        this.objectMapper = objectMapper;
        this.fuelPriceRepo = fuelPriceRepo;
        this.cityListRepo = cityListRepo;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    private void postConstruct() throws IOException {
        if (fuelPriceRepo.findAll().isEmpty()) {
            Resource resource = resourceLoader.getResource("classpath:FuelPrice.json");

            List<FuelPrice> fuelPrices = objectMapper.readValue(resource.getFile(), new TypeReference<List<FuelPrice>>() {
            });

            fuelPrices.forEach(price -> {
                price.setEntityId("FP-" + UUID.randomUUID().toString());

            });

            log.info("Saving fuel price into db");
            fuelPriceRepo.saveAll(fuelPrices);
        }


        if (cityListRepo.findAll().isEmpty()) {
            Resource resource = resourceLoader.getResource("classpath:Cities.json");
            CityList list = objectMapper.readValue(resource.getFile(), CityList.class);
            list.setEntityId(UUID.randomUUID().toString());
            log.info("Saving city data into db");
            cityListRepo.save(list);
        }


    }


}
