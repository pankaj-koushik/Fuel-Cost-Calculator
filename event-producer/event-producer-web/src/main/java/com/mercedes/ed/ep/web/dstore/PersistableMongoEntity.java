package com.mercedes.ed.ep.web.dstore;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Abstract model to keep common properties of diff models.
 */
@Data
public abstract class PersistableMongoEntity  {
    /**
     * Unique Id
     */
    @Id
    private String entityId;

    /**
     * @TODO -> Store which persona updated the record
     */
    private String entityModifiedBy;

    /**
     * @TODO
     * Last modified on
     */
    private Date entityModifiedDT;

    /**
     * @TODO -> Store which persona Created the record
     */
    private String entityPersistedBy;

    /**
     * Creation time.
     */
    private Date entityPersistDT;
}
