package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.payload.dto.SubscriptionPlanDTO;
import com.nisal.Library.Management.System.payload.response.ApiResponse;
import com.nisal.Library.Management.System.service.SubscriptionPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription-plans")
public class SubscriptionPlanController {
    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping
    public ResponseEntity<?> getAllSubscriptionPlans() {
        List<SubscriptionPlanDTO> planDTOS = subscriptionPlanService.getAllSubscriptionPlan();
        return ResponseEntity.ok(planDTOS);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<?> createSubscriptionPlan(
            @Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO
    ) throws Exception {
        SubscriptionPlanDTO planDTO = subscriptionPlanService.createSubscriptionPlan(subscriptionPlanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(planDTO);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateSubscriptionPlan(
            @RequestBody SubscriptionPlanDTO subscriptionPlanDTO,
            @PathVariable Long id
    ) throws Exception {
        SubscriptionPlanDTO planDTO = subscriptionPlanService.updateSubscriptionPlan(
                id, subscriptionPlanDTO
        );
        return ResponseEntity.ok(planDTO);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteSubscriptionPlan(
            @PathVariable Long id
    ) throws Exception {
        subscriptionPlanService.deleteSubscriptionPlan(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Successfully deleted Subscription Plan");
        response.setStatus(true);
        return ResponseEntity.ok(response);
    }

}
