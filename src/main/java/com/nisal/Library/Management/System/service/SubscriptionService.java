package com.nisal.Library.Management.System.service;

import com.nisal.Library.Management.System.payload.dto.SubscriptionDTO;

import java.awt.print.Pageable;
import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO subscribe(SubscriptionDTO dto) throws Exception;
    SubscriptionDTO getUserActiveSubscription(Long userId);
    SubscriptionDTO cancelSubscription(Long subscriptionId, String reason);
    SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId);
    List<SubscriptionDTO> getAllSubscriptions(Pageable pageable);

}
