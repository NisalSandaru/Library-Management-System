package com.nisal.Library.Management.System.payload.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    private Long id;

    @NotBlank(message = "Plan code is mandatory")
    private String planCode;

    @NotBlank(message = "Name code is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Duration is mandatory")
    @Positive(message = "Duration must be positive")
    private Integer durationDays;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Long price;

    private String currency;

    @NotNull(message = "Max books allowed is mandatory")
    @Positive(message = "Max books must be positive")
    private Integer maxBooksAllowed;

    @NotNull(message = "Max days per book is mandatory")
    @Positive(message = "Max days must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder;

    private Boolean isActive;
    private Boolean isFeatured;
    private String badgeText;
    private String adminNotes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String createdBy;
    private String updatedBy;
}
