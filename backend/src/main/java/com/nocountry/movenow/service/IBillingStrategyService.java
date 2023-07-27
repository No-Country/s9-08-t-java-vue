package com.nocountry.movenow.service;
import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.model.Moving;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IBillingStrategyService {

    @Transactional
    BillingStrategy save(int numberOfHelpers, int hsQuantity, String vehicleType, Boolean insurance);

    BillingStrategy findById(Long billingStrategyId);
    BillingStrategy update(BillingStrategy billingStrategy);
    boolean delete(Long billingStrategyId);

    Double cost(int numberOfHelpers, int hsQuantity, String vehicleType , Boolean insurance);
    double getVehiclePrice(String vehicleType);
}
