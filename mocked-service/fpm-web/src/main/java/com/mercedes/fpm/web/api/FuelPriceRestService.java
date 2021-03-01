package com.mercedes.fpm.web.api;

import com.mercedes.fpm.reponse.FuelPriceResponse;
import com.mercedes.fpm.web.service.FuelPriceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuelPriceRestService {

    final
    FuelPriceService service;

    public FuelPriceRestService(FuelPriceService service) {
        this.service = service;
    }


    @GetMapping("/fuelprice")
    public ResponseEntity<FuelPriceResponse> getPriceList() throws Exception {

        return new ResponseEntity<>(service.findAllPrice(), HttpStatus.CREATED);

    }
}
