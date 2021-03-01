package com.mercedes.ed.ep.web.job;

import com.mercedes.ed.ep.ety.entity.FuelDetails;
import com.mercedes.ed.ep.web.config.AppConfig;
import com.mercedes.ed.ep.web.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
@Slf4j
public class Scheduler {

    final
    ProducerService service;

    final RestTemplate restTemplate;


    public Scheduler(ProducerService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    /**
     * fixedRate = 120000 (ms) -> cron will be run @ every 2 min
     * @throws Exception
     */
    @Scheduled(fixedRate = 120000,initialDelay = 60000)
    public void cronJobSch() throws Exception {

      log.info("Schedule occurred @  {}",new Date());
        Random rand = new Random();

        service.publishEvt(new FuelDetails(rand.nextBoolean(),
                /**
                 * Picking random City
                 */
                AppConfig.cityList.get(rand.nextInt(AppConfig.cityList.size())),

                /**
                 * Picking random Car Id.
                 * Kept Car Id to 2 digits, so possibility of repeating car ids will be high
                 */
                String.valueOf(rand.ints(0, 20).findFirst().getAsInt())
        ));
    }
}
