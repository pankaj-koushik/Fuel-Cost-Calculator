package com.mercedes.ed.ep.ety.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelDetails {

    private boolean fuellid;
    @NotNull(message = "Please Enter city name")
    private String city;
    private String carId;


}
