package com.nocountry.movenow.controller;

import com.nocountry.movenow.service.impl.BillingStrategyServiceImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/billing")
@RestController
public class BillingStrategyController {

    private final BillingStrategyServiceImpl billingStrategyService ;

    public BillingStrategyController(BillingStrategyServiceImpl billingStrategyService) {
        this.billingStrategyService = billingStrategyService;
    }

    @GetMapping("/cost")
    public String cost(@Param("numberOfHelpers") int numberOfHelpers, @Param("hsQuantity") int hsQuantity, @Param("vehicleType") String vehicleType) {

        return "the cost is " + billingStrategyService.cost(numberOfHelpers, hsQuantity, vehicleType);
    }
}
