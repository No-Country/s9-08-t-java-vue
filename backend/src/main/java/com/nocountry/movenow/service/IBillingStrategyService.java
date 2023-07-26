package com.nocountry.movenow.service;
import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.model.Moving;
import java.util.Optional;

public interface IBillingStrategyService {


    BillingStrategy save(int numberOfHelpers, int hsQuantity, String vehicleType, Long movingId);

    BillingStrategy findById(Long billingStrategyId);
    BillingStrategy update(BillingStrategy billingStrategy);
    boolean delete(Long billingStrategyId);

    double cost(int numberOfHelpers, int hsQuantity, String vehicleType);

    double getVehiclePrice(String vehicleType);
}
