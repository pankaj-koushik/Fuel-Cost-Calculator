package com.mercedes.ed.fcc.repo;

import com.mercedes.ed.fcc.model.CarDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repo to perform CRUD operations on Model CarDetails.
 */
public interface ConsumerRepo  extends MongoRepository<CarDetails,String> {
}
