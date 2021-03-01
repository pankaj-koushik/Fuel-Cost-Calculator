package com.mercedes.fpm.web.service;

import com.mercedes.fpm.reponse.CityListResponse;
import com.mercedes.fpm.web.repo.CityListRepo;
import org.springframework.stereotype.Service;

@Service
public class CityListServiceImpl implements CityListService {

    final CityListRepo cityListRepo;

    public CityListServiceImpl(CityListRepo cityListRepo) {
        this.cityListRepo = cityListRepo;
    }

    @Override
    public CityListResponse findAllCities() {
        CityListResponse response = new CityListResponse();
        response.setCities(cityListRepo.findAll().get(0).getCities());

        return response;
    }
}
