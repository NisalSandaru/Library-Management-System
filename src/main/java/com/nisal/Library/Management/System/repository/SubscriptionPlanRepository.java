package com.nisal.Library.Management.System.repository;

import com.nisal.Library.Management.System.model.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

    Boolean existsByPlanCode(String planCode);
}
