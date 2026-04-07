package com.nisal.Library.Management.System.service.impl;

import com.nisal.Library.Management.System.mapper.SubscriptionMapper;
import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.payload.dto.SubscriptionDTO;
import com.nisal.Library.Management.System.repository.SubscriptionRepository;
import com.nisal.Library.Management.System.service.SubscriptionService;
import com.nisal.Library.Management.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;

    @Override
    public SubscriptionDTO subscribe(SubscriptionDTO dto) throws Exception {
        User user = userService.getCurrentUser();
        return null;
    }

    @Override
    public SubscriptionDTO getUserActiveSubscription(Long userId) {
        return null;
    }

    @Override
    public SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) {
        return null;
    }

    @Override
    public SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId) {
        return null;
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions(Pageable pageable) {
        return List.of();
    }
}
