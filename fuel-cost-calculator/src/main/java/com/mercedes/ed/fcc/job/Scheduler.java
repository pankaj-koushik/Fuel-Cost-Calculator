package com.mercedes.ed.fcc.job;

import com.mercedes.ed.fcc.config.AppConfig;
import com.mercedes.fpm.reponse.FuelPriceDTO;
import com.mercedes.fpm.reponse.FuelPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class Scheduler {

    /**
     * Mocked Server (Fuel Price Management) endpoint.
     */
    @Value(value = "${app.mocked-server-url}")
    private String serverUrl;

    final RestTemplate restTemplate;

    public Scheduler( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Every mid night update fuel prices in the cache.
     * @throws Exception
     */
    @Scheduled(cron = "0 5 0 * * ?")
    public void cronJobSch() throws Exception {

        log.info("Schedule occurred to update price @  {}",new Date());


        ResponseEntity<FuelPriceResponse> forEntity = restTemplate.getForEntity(serverUrl, FuelPriceResponse.class);

        List<FuelPriceDTO> prices = Objects.requireNonNull(forEntity.getBody()).getFuelPrices();

        AppConfig.setFuelPrices(prices.stream().collect(Collectors.toMap(FuelPriceDTO::getDistrict,FuelPriceDTO::getProducts)));


    }
}
