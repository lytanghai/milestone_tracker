package com.financial.milestone_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.milestone_tracker.constant.ApplicationConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateMilestoneConditionRequestDto {

    @NotBlank
    @JsonProperty("metric_type")
    private String metricType;

    @NotBlank
    @JsonProperty("operator")
    private String operator;

    @NotNull
    @JsonProperty("target_value")
    private BigDecimal targetValue;

    @JsonProperty("sequence_no")
    private Integer sequenceNo;

    private String status = ApplicationConstant.ACTIVE;
}