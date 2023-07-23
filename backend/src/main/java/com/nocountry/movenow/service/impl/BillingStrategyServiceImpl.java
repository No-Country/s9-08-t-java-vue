package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.repository.BillingStrategyRepository;
import com.nocountry.movenow.service.IBillingStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BillingStrategyServiceImpl implements IBillingStrategyService {


    @Autowired
    public BillingStrategyRepository billingStrategyRepository;

    public BillingStrategyServiceImpl(BillingStrategyRepository billingStrategyRepository) {
        this.billingStrategyRepository = billingStrategyRepository;
    }


    @Override
    public BillingStrategy save(double helperValue, double vehicleValue, double insuranceValue, int numberOfHelpers,
                                int chargingHours, int downloadHours, int travelHours, double packaging) {

        return billingStrategyRepository.save(new BillingStrategy(helperValue,  vehicleValue, insuranceValue, numberOfHelpers,
         chargingHours, downloadHours, travelHours, packaging));
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
           billingSObj.setHelperValue(billingStrategy.getHelperValue() > 0 ? billingStrategy.getHelperValue() : billingStrategyOptional.get().getHelperValue());
           billingSObj.setVehicleValue(billingStrategy.getVehicleValue() > 0?billingStrategy.getVehicleValue():billingStrategyOptional.get().getVehicleValue());
           billingSObj.setNumberOfHelpers(billingStrategy.getNumberOfHelpers() > 0? billingStrategy.getNumberOfHelpers():billingStrategyOptional.get().getNumberOfHelpers());
           billingSObj.setChargingHours(billingStrategy.getChargingHours() > 0? billingStrategy.getChargingHours():billingStrategyOptional.get().getChargingHours());
           billingSObj.setDownloadHours(billingStrategy.getDownloadHours() > 0? billingStrategy.getDownloadHours():billingStrategyOptional.get().getDownloadHours());
           billingSObj.setTravelHours(billingStrategy.getTravelHours() > 0? billingStrategy.getTravelHours():billingStrategyOptional.get().getTravelHours());
           billingSObj.setPackaging(billingStrategy.getPackaging() >0? billingStrategy.getPackaging(): billingStrategyOptional.get().getPackaging());

           return billingStrategyRepository.save(billingStrategy);


        }else {
            throw new RuntimeException("billing not found");
        }
    }


    @Override
    public boolean delete(Long billingStrategyId) {

        if(billingStrategyRepository.findById(billingStrategyId).isPresent()){

            BillingStrategy billingStrategy = billingStrategyRepository.findById(billingStrategyId).get();
            billingStrategy.setSoftDelete(true);
            billingStrategyRepository.save(billingStrategy);

            return true;
        }else{

            return false;
        }
    }

    @Override
    public double cost(BillingStrategy billingStrategy){

        return ((billingStrategy.getHelperValue() * billingStrategy.getNumberOfHelpers()) + billingStrategy.getVehicleValue() )
                * (billingStrategy.getChargingHours() + billingStrategy.getDownloadHours() + billingStrategy.getTravelHours())
                + billingStrategy.getInsuranceValue() + billingStrategy.getPackaging();

    }
}
