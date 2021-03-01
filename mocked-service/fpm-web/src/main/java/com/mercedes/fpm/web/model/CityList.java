package com.mercedes.fpm.web.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("cities")
@Data
public class CityList extends PersistableMongoEntity {

    private  List<String> cities = new ArrayList<>();
}
