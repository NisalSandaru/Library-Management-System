package com.nisal.Library.Management.System.service;

import com.nisal.Library.Management.System.payload.dto.SubscriptionDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscriptionService {

    SubscriptionDTO subscribe(SubscriptionDTO dto) throws Exception;
    SubscriptionDTO getUserActiveSubscription(Long userId) throws Exception;
    SubscriptionDTO cancelSubscription(Long subscriptionId, String reason);
    SubscriptionDTO activateSubscription(Long subscriptionId, Long paymentId);
    List<SubscriptionDTO> getAllSubscriptions(Pageable pageable);
    void deactivateExpiredSubscriptions();

}
