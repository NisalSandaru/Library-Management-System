package com.nisal.Library.Management.System.service.impl;

import com.nisal.Library.Management.System.mapper.SubscriptionPlanMapper;
import com.nisal.Library.Management.System.model.SubscriptionPlan;
import com.nisal.Library.Management.System.model.User;
import com.nisal.Library.Management.System.payload.dto.SubscriptionPlanDTO;
import com.nisal.Library.Management.System.repository.SubscriptionPlanRepository;
import com.nisal.Library.Management.System.service.SubscriptionPlanService;
import com.nisal.Library.Management.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository planRepository;
    private final SubscriptionPlanMapper planMapper;
    private final UserService userService;

    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception {
        if (planRepository.existsByPlanCode(planDTO.getPlanCode())) {
            throw new Exception("Plan code is already exist");
        }
        SubscriptionPlan plan = planMapper.toEntity(planDTO);

        User currentUser=userService.getCurrentUser();
        plan.setCreatedBy(currentUser.getFullName());
        SubscriptionPlan savedPlan = planRepository.save(plan);

        return planMapper.toDTO(savedPlan);
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) throws Exception {
        SubscriptionPlan existingPlan = planRepository.findById(planId)
                .orElseThrow(()-> new Exception("Plan not found!"));
        planMapper.updateEntity(existingPlan, planDTO);
        User currentUser=userService.getCurrentUser();
        existingPlan.setUpdatedBy(currentUser.getFullName());
        SubscriptionPlan updatedPlan = planRepository.save(existingPlan);
        return planMapper.toDTO(updatedPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long planId) throws Exception {
        SubscriptionPlan existingPlan = planRepository.findById(planId)
                .orElseThrow(()-> new Exception("Plan not found!"));
        planRepository.delete(existingPlan);
    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlan() {
        List<SubscriptionPlan> planList = planRepository.findAll();

        return planList.stream().map(
                planMapper::toDTO
        ).collect(Collectors.toList());
    }
}
