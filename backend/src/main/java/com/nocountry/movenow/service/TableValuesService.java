package com.nocountry.movenow.service;

import com.nocountry.movenow.model.TableValues;

public interface TableValuesService {
    TableValues save(Double crewPrice, Double insurance, Double vehiclePrice, Double hsPrice);
}
