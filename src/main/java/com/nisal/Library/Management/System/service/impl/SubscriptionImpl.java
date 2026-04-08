package com.nisal.Library.Management.System.service.impl;

import com.nisal.Library.Management.System.exception.SubscriptionException;
import com.nisal.Library.Management.System.mapper.SubscriptionMapper;
import com.nisal.Library.Management.System.model.Subscription;
import com.nisal.Library.Management.System.model.SubscriptionPlan;
import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.payload.dto.SubscriptionDTO;
import com.nisal.Library.Management.System.repository.SubscriptionPlanRepository;
import com.nisal.Library.Management.System.repository.SubscriptionRepository;
import com.nisal.Library.Management.System.service.SubscriptionService;
import com.nisal.Library.Management.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionDTO subscribe(SubscriptionDTO dto) throws Exception {
        User user = userService.getCurrentUser();

        SubscriptionPlan plan = subscriptionPlanRepository
                .findById(dto.getPlanId())
                .orElseThrow(() -> new Exception("Plan Not Found"));

        Subscription subscription = subscriptionMapper.toEntity(dto);
        subscription.initializeFromPlan();
        subscription.setIsActive(false);
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        return subscriptionMapper.toDto(savedSubscription);
    }

    @Override
    public SubscriptionDTO getUserActiveSubscription(Long userId) throws Exception {
        User user = userService.getCurrentUser();

        Subscription subscription = subscriptionRepository
                .findActiveSubscriptionByUserId(user.getId(), LocalDate.now())
                .orElseThrow(() -> new SubscriptionException("Subscription Not Found"));
        return subscriptionMapper.toDto(subscription);
    }

    @Override
    public SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionException("Subscription Not Found with ID: " + subscriptionId));

        if (!subscription.getIsActive()) {
            throw new SubscriptionException("Subscription is Already Inactive");
        }

        subscription.setIsActive(false);
        subscription.setCancelledAt(LocalDateTime.now());
        subscription.setCancellationReason(reason != null ? reason : "Cancelled By User");
        subscriptionRepository.save(subscription);

        return subscriptionMapper.toDto(subscription);
    }

    @Override
    public SubscriptionDTO activateSubscription(Long subscriptionId, Long paymentId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionException("Subscription Not Found with ID: " + subscriptionId));
        //verify payment (todo)

        subscription.setIsActive(true);
        subscription = subscriptionRepository.save(subscription);
        return subscriptionMapper.toDto(subscription);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions(Pageable pageable) {
        List<Subscription> subscriptions=subscriptionRepository.findAll();

        return subscriptionMapper.toDtoList(subscriptions);
    }

    @Override
    public void deactivateExpiredSubscriptions() {
        List<Subscription> expiredSubscriptions = subscriptionRepository
                .findExpiredActiveSubscriptions(LocalDate.now());

        for (Subscription subscription : expiredSubscriptions) {
            subscription.setIsActive(false);
            subscriptionRepository.save(subscription);
        }
    }
}
