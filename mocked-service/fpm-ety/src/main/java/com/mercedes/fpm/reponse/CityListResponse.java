package com.mercedes.fpm.reponse;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CityListResponse {

    private String message;
    private List<String> cities = new ArrayList<>();
}
