package com.nocountry.movenow.service;
import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.model.Moving;
import java.util.Optional;

public interface IBillingStrategyService {

    BillingStrategy save(double helperValue, double vehicleValue, double insuranceValue, int numberOfHelpers,
                         int hsQuantity, double packaging, Long movingId);

    BillingStrategy findById(Long billingStrategyId);
    BillingStrategy update(BillingStrategy billingStrategy);
    boolean delete(Long billingStrategyId);
    public double cost(BillingStrategy billingStrategy);
}
