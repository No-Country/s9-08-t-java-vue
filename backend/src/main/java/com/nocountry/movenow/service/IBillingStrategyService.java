package com.nocountry.movenow.service;
import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.model.Moving;
import java.util.Optional;

public interface IBillingStrategyService {

    BillingStrategy save(BillingStrategy billingStrategy);
    BillingStrategy findById(Long billingStrategyId);
    BillingStrategy update(BillingStrategy billingStrategy);
    void delete(Long billingStrategyId);
    public double cost();
}
