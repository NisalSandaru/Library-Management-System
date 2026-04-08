package com.nisal.Library.Management.System.controller;

import com.nisal.Library.Management.System.model.Subscription;
import com.nisal.Library.Management.System.payload.dto.SubscriptionDTO;
import com.nisal.Library.Management.System.payload.response.ApiResponse;
import com.nisal.Library.Management.System.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService  subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(
            @Valid @RequestBody SubscriptionDTO subscription) throws Exception {
        SubscriptionDTO dto = subscriptionService.subscribe(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/user/active")
    public ResponseEntity<?> getUserActiveSubscriptions(
            @RequestParam(required = false) Long userId
    ) throws Exception {
        SubscriptionDTO dto = subscriptionService
                .getUserActiveSubscription(userId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("cancel/{subscriptionId}")
    public ResponseEntity<?> cancelSubscription(
            @PathVariable Long subscriptionId,
            @RequestParam(required = false) String reason
    ){
        SubscriptionDTO dto = subscriptionService
                .cancelSubscription(subscriptionId, reason);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("activate")
    public ResponseEntity<?> activateSubscription(
            @RequestParam Long subscriptionId,
            @RequestParam Long paymentId
    ){
        SubscriptionDTO dto = subscriptionService.activateSubscription(subscriptionId, paymentId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAllSubscriptions() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<SubscriptionDTO> dtoList = subscriptionService.getAllSubscriptions(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/admin/deactivate-Expired")
    public ResponseEntity<?> deactivateExpiredSubscriptions() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        subscriptionService.getAllSubscriptions(pageable);
        ApiResponse res = new ApiResponse("Task done!", true);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


}
