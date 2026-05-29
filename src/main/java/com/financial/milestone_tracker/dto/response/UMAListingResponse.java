package com.financial.milestone_tracker.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UMAListingResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_enabled")
    private Boolean isEnabled;

    @JsonProperty("enabled_at")
    private LocalDateTime enabledAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("last_updated_at")
    private LocalDateTime lastUpdatedAt;
}