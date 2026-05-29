package com.financial.milestone_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UMAListingFilterRequestDto {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_enabled")
    private boolean isEnabled;

    @JsonProperty("sort_by")
    private String sortBy = "createdAt";

    @JsonProperty("sort_direction")
    private String sortDirection = "DESC";

    private Integer page = 0;

    private Integer size = 10;
}