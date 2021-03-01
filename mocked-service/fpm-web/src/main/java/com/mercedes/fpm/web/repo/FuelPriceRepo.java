package com.mercedes.fpm.web.repo;

import com.mercedes.fpm.web.model.FuelPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repo to save Fuel Price.
 */
public interface FuelPriceRepo extends MongoRepository<FuelPrice,String> {
}
