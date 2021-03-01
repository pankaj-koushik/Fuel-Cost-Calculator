package com.mercedes.fpm.web.repo;

import com.mercedes.fpm.web.model.CityList;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * Repo to save City List.
 */
public interface CityListRepo extends MongoRepository<CityList,String> {
}
