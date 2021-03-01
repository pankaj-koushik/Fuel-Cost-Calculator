package com.mercedes.ed.fcc.config;

import com.mercedes.fpm.reponse.FuelPriceDTO;
import com.mercedes.fpm.reponse.FuelPriceResponse;
import com.mercedes.fpm.reponse.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class AppConfig  implements ApplicationListener<ApplicationReadyEvent> {


    /**
     * Mocked Server (Fuel Price Management) endpoint.
     */
    @Value(value = "${app.mocked-server-url}")
    private  String FPM_SERVER_URL;

    public AppConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    final
    RestTemplate restTemplate ;


    /**
     * Fuel prices cache
     */
    private static Map<String,List<ProductDTO>> fuelPrices;

    /**
     * Fetch fuel Price from Mock Server when application started
     * @param event
     */

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {



            ResponseEntity<FuelPriceResponse> forEntity = restTemplate.getForEntity(FPM_SERVER_URL, FuelPriceResponse.class);

            List<FuelPriceDTO> prices = Objects.requireNonNull(forEntity.getBody()).getFuelPrices();

            fuelPrices= prices.stream().collect(Collectors.toMap(FuelPriceDTO-> FuelPriceDTO.getDistrict().toLowerCase(),FuelPriceDTO::getProducts));



    }

    public static Map<String, List<ProductDTO>> getFuelPrices() {
        return fuelPrices;
    }

    public static void setFuelPrices(Map<String, List<ProductDTO>> fuelPrices) {
        AppConfig.fuelPrices = fuelPrices;
    }
}
