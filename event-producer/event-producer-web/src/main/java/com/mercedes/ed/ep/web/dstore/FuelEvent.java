package com.mercedes.ed.ep.web.dstore;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("fuel_event")
/**
 * Event Store model -> Save all the events in permanent storage.
 */
public class FuelEvent extends PersistableMongoEntity{

    /**
     * Boolean value which indicates car fuel lid status.
     * true  -> lid opened
     * false -> lid closed
     */
    private boolean fuellid;

    /**
     * City of which fuel price will be used to calculate cost
     */
    private String city;

    private String carId;
}
