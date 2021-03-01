package com.mercedes.ed.ep.web.service.impl;

import com.mercedes.ed.ep.ety.entity.AbstractEvent;
import com.mercedes.ed.ep.ety.entity.FuelDetails;
import com.mercedes.ed.ep.ety.entity.FuelAddedEvent;
import com.mercedes.ed.ep.web.dstore.FuelEvent;
import com.mercedes.ed.ep.web.repo.EventRepo;
import com.mercedes.ed.ep.web.service.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, AbstractEvent> kafkaTemplate;

    private final EventRepo eventRepo;

    private final ObjectMapper objectMapper;

    /**
     * Topic name to which all the events will be published.
     */
    @Value(value = "${app.kafka.topic}")
    private String topicName;

    public ProducerServiceImpl(KafkaTemplate<String, AbstractEvent> kafkaTemplate, EventRepo eventRepo, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.eventRepo = eventRepo;
        this.objectMapper = objectMapper;
    }

    /**
     * save Event into Mongo and publish message to kafka
     * @param req
     */

    @Override
    public void publishEvt(FuelDetails req)  {

        FuelAddedEvent event = objectMapper.convertValue(req, FuelAddedEvent.class);
        event.setFuellid(req.isFuellid());
        event.setId("EV-"+UUID.randomUUID().toString());
        event.setCity(req.getCity());

        FuelEvent fuelEvent = objectMapper.convertValue(event,FuelEvent.class);
        fuelEvent.setEntityId(event.getId());
        eventRepo.save(fuelEvent);

        log.info("Saved event into db with id {}",event.getId());

        try {
            kafkaTemplate.send(topicName, event);
        }
        catch (RuntimeException e){
            log.error("Exception while publishing message to KAFKA",e);
            new RuntimeException("Error while processing request");
        }
    }


}
