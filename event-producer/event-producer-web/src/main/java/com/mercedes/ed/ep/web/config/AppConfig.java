package com.mercedes.ed.ep.web.config;

import com.mercedes.fpm.reponse.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class AppConfig  implements ApplicationListener<ApplicationReadyEvent> {



    public static String SERVER_URL;

    @Value(value = "${app.mocked-server-url}")
    public void setServerUrl(String serverUrl){
        SERVER_URL = serverUrl;
    }

    static RestTemplate restTemplate = new RestTemplate();

    /**
     * Cached it in memory.
     */
    public static List<String> cityList;



    /**
     * On Application Bootstrapping loading all the cities for validations / publishing events.
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

            ResponseEntity<CityListResponse> forEntity = restTemplate.getForEntity(SERVER_URL, CityListResponse.class);
            cityList = Objects.requireNonNull(forEntity.getBody()).getCities();

    }
}

