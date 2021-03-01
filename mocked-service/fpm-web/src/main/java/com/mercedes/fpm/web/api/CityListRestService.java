package com.mercedes.fpm.web.api;

import com.mercedes.fpm.reponse.CityListResponse;
import com.mercedes.fpm.web.service.CityListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mocked/cities/api/v1")
public class CityListRestService {
    final
    CityListService service;

    public CityListRestService(CityListService service) {
        this.service = service;
    }


    @GetMapping("/cities")
    public ResponseEntity<CityListResponse> getCities() throws Exception {

        return new ResponseEntity<>(service.findAllCities(), HttpStatus.OK);

    }
}
