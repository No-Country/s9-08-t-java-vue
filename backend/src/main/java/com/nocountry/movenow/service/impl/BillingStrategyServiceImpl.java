package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.BillingStrategy;

import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.repository.BillingStrategyRepository;
import com.nocountry.movenow.service.IBillingStrategyService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingStrategyServiceImpl implements IBillingStrategyService {


public BillingStrategy billingStrategy;

public BillingStrategyRepository billingStrategyRepository;

    public BillingStrategyServiceImpl(BillingStrategyRepository billingStrategyRepository) {
        this.billingStrategyRepository = billingStrategyRepository;
    }



    @Override
    public BillingStrategy save(BillingStrategy billingStrategy) {

        return billingStrategyRepository.save(billingStrategy);
    }

    @Override
    public BillingStrategy findById(Long billingStrategyId) {
        return billingStrategyRepository.findById(billingStrategyId).get();
    }

    @Override
    public BillingStrategy update(BillingStrategy billingStrategy) {
        Long id = billingStrategy.getBillingtrategy_id();
        Optional<BillingStrategy> billingStrategyOptional = billingStrategyRepository.findById(id);

        if (billingStrategyOptional.isPresent()) {
            BillingStrategy billingSObj = billingStrategyOptional.get();
           billingSObj.setBillingtrategy_id(billingStrategy.getBillingtrategy_id()!= null ? billingStrategy.getBillingtrategy_id() : billingStrategyOptional.get().getBillingtrategy_id()) ;
          // billingSObj.setHelperValue(billingStrategy.getHelperValue() != null? billingStrategy.getHelperValue() : billingStrategyOptional.get().getHelperValue());
           // billingSObj.setNumberOfHelpers(billingStrategy.getNumberOfHelpers() != null? billingStrategy.getNumberOfHelpers():billingStrategyOptional.get().getNumberOfHelpers());



        }
        return null;
    }

    @Override
    public void delete(Long billingStrategyId) {

    }

    @Override
    public double cost(){
        return ((billingStrategy.getHelperValue() * billingStrategy.getNumberOfHelpers()) + billingStrategy.getVehicleValue() + billingStrategy.getInsuranceValue())
                * (billingStrategy.getChargingHours() + billingStrategy.getDownloadHours() + billingStrategy.getTravelHours());

    }
}
