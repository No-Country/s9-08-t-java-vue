package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.TableValues;
import com.nocountry.movenow.service.TableValuesService;

public class TableValuesServiceImpl implements TableValuesService {


    private final MovingServiceImpl movingService;

    public TableValuesServiceImpl(MovingServiceImpl movingService) {
        this.movingService = movingService;
    }

    @Override
    public TableValues save(Double crewPrice, Double insurance, Double vehiclePrice, Double hsPrice) {

        TableValues tableValues = new TableValues( crewPrice, insurance, vehiclePrice, hsPrice);

        return tableValues;

    }

}
