package com.financial.milestone_tracker.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MilestoneResponse {
    private Long id;

    private String code;

    private String title;

    private String titleKh;

    private String description;

    private String descriptionKh;

    private String iconUrl;

    private String status;

    private List<MilestoneConditionResponse> conditions;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    @Data
    public static class MilestoneConditionResponse {

        private Long id;

        private String metricType;

        private String operatorType;

        private BigDecimal targetValue;

        private Integer sequenceNo;

        private String status;
    }
}
