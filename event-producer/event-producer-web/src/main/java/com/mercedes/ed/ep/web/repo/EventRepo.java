package com.mercedes.ed.ep.web.repo;

import com.mercedes.ed.ep.web.dstore.FuelEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repo to perform CRUD operations on Model FuelEvent.
 */
public interface EventRepo  extends MongoRepository<FuelEvent,String> {
}
