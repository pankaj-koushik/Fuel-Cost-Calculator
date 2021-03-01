package com.mercedes.ed.ep.web.service;

import com.mercedes.ed.ep.ety.entity.FuelDetails;


public interface ProducerService {

    /**
     * If request is valid, Publishes event to Kafka.
     * @param req
     */
    void publishEvt(FuelDetails req) throws Exception;
}
