package com.nocountry.movenow.repository;

import com.nocountry.movenow.model.BillingStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingStrategyRepository extends JpaRepository<BillingStrategy,Long> {

}
