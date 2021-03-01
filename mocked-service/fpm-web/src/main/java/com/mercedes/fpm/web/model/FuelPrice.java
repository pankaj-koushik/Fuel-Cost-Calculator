package com.mercedes.fpm.web.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("fuel_price")
public class FuelPrice  extends PersistableMongoEntity{

     private String district;
     private List<Product> products = new ArrayList<>();
}
