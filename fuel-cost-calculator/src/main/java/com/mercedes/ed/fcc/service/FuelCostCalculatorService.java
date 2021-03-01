package com.mercedes.ed.fcc.service;

import com.mercedes.ed.ep.ety.entity.AbstractEvent;
import com.mercedes.ed.ep.ety.entity.FuelAddedEvent;
import com.mercedes.ed.fcc.config.AppConfig;
import com.mercedes.ed.fcc.dto.CarDetailsDTO;
import com.mercedes.ed.fcc.dto.FuelCostDTO;
import com.mercedes.fpm.reponse.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class FuelCostCalculatorService {

    final RestTemplate restTemplate;

    final ConsumerService consumerService;


    public FuelCostCalculatorService(RestTemplate restTemplate, ConsumerService consumerService) {
        this.restTemplate = restTemplate;
        this.consumerService = consumerService;
    }

    /**
     * Kafka Listener for FuelAdded event.
     *
     * @param message
     * @param <T>
     */
    @KafkaListener(topics = "${app.kafka.topic}", groupId = "${app.kafka.group-id}")
    public <T extends AbstractEvent> void listenMsg(T message) {

        log.info("Received evt with id {}", message.getMessageId());

        if (message.getEventType().equals(FuelAddedEvent.class.getName())) {
            FuelAddedEvent fuelAddedEvent = (FuelAddedEvent) message;

            /**
             * No action if fuel-lid is closed.
             */
            if (!fuelAddedEvent.isFuellid()) {
                log.info("Event : {}", fuelAddedEvent);
                return;
            }

            /**
             * Get fuel price from cache
             */
            Optional<ProductDTO> petrolPrice = AppConfig.getFuelPrices().get(fuelAddedEvent.getCity().toLowerCase()).stream().filter(prod -> prod.getProductName().equals("Petrol")).findFirst();
            if (petrolPrice.isPresent()) {
                CarDetailsDTO carDetailsDTO = new CarDetailsDTO();
                carDetailsDTO.setCarId(fuelAddedEvent.getCarId());
                FuelCostDTO fuelCostDTO = new FuelCostDTO();
                fuelCostDTO.setFuelPrice(petrolPrice.get().getProductPrice());
                fuelCostDTO.setCity(fuelAddedEvent.getCity());
                fuelCostDTO.setCreatedDate(new Date());
                /**
                 * assuming that the car tank is open for 2 mins
                 */

                fuelCostDTO.setCost( Double.parseDouble(String.format("%.6f", petrolPrice.get().getProductPrice() * 4)));
                carDetailsDTO.setFuelCost(fuelCostDTO);
                consumerService.save(carDetailsDTO);
            }


        }


    }
}
