package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.BillingStrategy;
import com.nocountry.movenow.model.TableValues;
import com.nocountry.movenow.repository.BillingStrategyRepository;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.service.IBillingStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BillingStrategyServiceImpl implements IBillingStrategyService {

    private final BillingStrategyRepository billingStrategyRepository;



    @Autowired
    public BillingStrategyServiceImpl( BillingStrategyRepository billingStrategyRepository) {
        this.billingStrategyRepository = billingStrategyRepository;
          }


    @Override
    @Transactional
    public BillingStrategy save(int numberOfHelpers, int hsQuantity, String vehicleType, Boolean insurance) {

        if (numberOfHelpers <= 0 || hsQuantity <= 0 || vehicleType == null) {
            throw new RuntimeException("Invalid input");
        }

        BillingStrategy billingStrategy = new BillingStrategy(numberOfHelpers, hsQuantity, vehicleType, insurance);

        Double calculatedCost = cost(numberOfHelpers, hsQuantity, vehicleType, insurance);

        if (Double.isNaN(calculatedCost) || Double.isInfinite(calculatedCost)) {
            throw new RuntimeException("Invalid cost calculation");
            // Or set a default value: calculatedCost = 0.0;
        }

        billingStrategy.setFinalCost(calculatedCost);

        return billingStrategyRepository.save(billingStrategy);
    }

    @Override
    public BillingStrategy findById(Long billingStrategyId) {
        return billingStrategyRepository.findById(billingStrategyId).get();
    }

    @Override
    public BillingStrategy update(BillingStrategy billingStrategy) {
        Long id = billingStrategy.getId();
        Optional<BillingStrategy> billingStrategyOptional = billingStrategyRepository.findById(id);

        if (billingStrategyOptional.isPresent()) {
            BillingStrategy billingSObj = billingStrategyOptional.get();

           billingSObj.setId(billingStrategy.getId()!= null ? billingStrategy.getId() : billingStrategyOptional.get().getId()); ;
           billingSObj.setNumberOfHelpers(billingStrategy.getNumberOfHelpers() > 0? billingStrategy.getNumberOfHelpers():billingStrategyOptional.get().getNumberOfHelpers());
           billingSObj.setHsQuantity(billingStrategy.getHsQuantity() > 0? billingStrategy.getHsQuantity():billingStrategyOptional.get().getHsQuantity());
           billingSObj.setVehicleType(billingStrategy.getVehicleType() != null ? billingStrategy.getVehicleType():billingStrategyOptional.get().getVehicleType());

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
        }else {

            return false;
        }
    }


    @Override
    public Double cost(int numberOfHelpers, int hsQuantity, String vehicleType , Boolean insurance) {

        if (numberOfHelpers == 0 && hsQuantity == 0 && vehicleType == null) {
            throw new RuntimeException("No se puede calcular el costo");
        }

        TableValues tableValues = TableValues.getInstance();

        return (((tableValues.getCrewPrice() * numberOfHelpers ) + getVehiclePrice(vehicleType)) * (hsQuantity + 2)
                + getInsurancePrice( insurance ) );
    }

    @Override
    public double getVehiclePrice(String vehicleType) {

        //Format the input to lower case and replace "_" with " " for the table values
        vehicleType = vehicleType.toLowerCase().trim();
        vehicleType = vehicleType.replace("_", " ");
        TableValues tableValues = TableValues.getInstance();

        switch (vehicleType) {
            case "pick up":
                return tableValues.getPickUpPrice();
            case "truck":
                return tableValues.getTruckPrice();
            case "heavy truck":
                return tableValues.getHeavyTruckPrice();
            default:
                return 0;
        }


    }


    public double getInsurancePrice(Boolean insurance){

        TableValues tableValues = TableValues.getInstance();
        return insurance ? tableValues.getInsurance() : 0 ;
    }

}
