package com.mercedes.fpm.web.job;

import com.mercedes.fpm.web.model.FuelPrice;
import com.mercedes.fpm.web.repo.FuelPriceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class Scheduler {

    final FuelPriceRepo fuelPriceRepo;

    public Scheduler(FuelPriceRepo fuelPriceRepo) {
        this.fuelPriceRepo = fuelPriceRepo;
    }

    /**
     *  Update fuel Price every midnight
     * @throws Exception
     */


    @Scheduled(cron = "0 0 0 * * ?")
    public void cronJobSch() throws Exception {

        log.info("Schedule occurred to update price @  {}",new Date());

        List<FuelPrice> fuelPrices = fuelPriceRepo.findAll();
        Random random = new Random();
        fuelPrices.forEach(price->{
            price.getProducts().forEach(prod->{
                Double change = random.doubles(-10, 10)
                        .findFirst()
                        .getAsDouble();
                change= Double.parseDouble(String.format("%.2f", change));
                prod.setPriceChange(change);
                prod.setProductPrice(prod.getProductPrice()+change);
            });
        });

        fuelPriceRepo.saveAll(fuelPrices);


    }
}
