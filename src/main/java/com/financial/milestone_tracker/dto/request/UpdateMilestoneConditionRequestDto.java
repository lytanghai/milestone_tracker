package com.financial.milestone_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateMilestoneConditionRequestDto {

    @NotBlank
    @JsonProperty("metric_type")
    private String metricType;

    @NotBlank
    private String operator;

    @NotNull
    @JsonProperty("target_value")
    private BigDecimal targetValue;

    @JsonProperty("sequence_no")
    private Integer sequenceNo;

    private String status;
}