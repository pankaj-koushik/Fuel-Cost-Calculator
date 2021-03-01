package com.mercedes.ed.fcc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class FccApplication {

    public static void main(String[] args) {
        SpringApplication.run(FccApplication.class, args);
        log.info("*********** Started Fuel Cost Calculator App ***********");
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
