package com.mercedes.ed.ep.ety.entity;

import lombok.*;


@Getter
@Setter
public class FuelAddedEvent extends AbstractEvent {

    private boolean fuellid;
    private String city;
    private String id;
    private String carId;

    public FuelAddedEvent() {
        super(FuelAddedEvent.class.getName(), "event-producer-web");
    }
}
