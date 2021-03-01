package com.mercedes.ed.ep.ety.entity;

import lombok.Data;

import java.util.Date;
import java.util.UUID;



@Data
public abstract class AbstractEvent {

    /**
     * Unique Id to identify each event
     */
    private String messageId;

    /**
     * Timestamp of when event is produced
     */
    private Date timestamp;

    /**
     * Type of event, will be used to cast
     */
    private String eventType;

    /**
     * service name which produced this event.
     */
    private String source;

    public AbstractEvent(String eventType, String source) {
        /**
         * @TODO-> validations
         */
        this.timestamp = new Date();
        this.eventType = eventType;
        this.source = source;
        this.messageId = UUID.randomUUID().toString();

    }
}
