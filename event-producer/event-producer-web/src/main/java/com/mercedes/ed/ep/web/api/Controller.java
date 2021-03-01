package com.mercedes.ed.ep.web.api;

import com.mercedes.ed.ep.ety.entity.FuelDetails;
import com.mercedes.ed.ep.ety.req.CreateResponse;
import com.mercedes.ed.ep.web.config.AppConfig;
import com.mercedes.ed.ep.web.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/ep/api/v1")
public class Controller {

    final
    ProducerService service;

    public Controller(ProducerService service) {
        this.service = service;
    }


    /**
     * API to manually publish an Event to Kafka with Car Lid state, CarId and City Name.
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/event")
    public ResponseEntity<CreateResponse> publishEvent(@RequestBody @Valid FuelDetails request) throws Exception {

        Assert.isTrue(AppConfig.cityList.stream().anyMatch(request.getCity()::equalsIgnoreCase), "Invalid or un-supported city :" + request.getCity());
        service.publishEvt(request);

        return new ResponseEntity<>(new CreateResponse("Successfully published Event"), HttpStatus.CREATED);
    }


}
