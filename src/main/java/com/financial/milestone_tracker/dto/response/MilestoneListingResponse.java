package com.financial.milestone_tracker.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MilestoneListingResponse {

    private Long id;

    private String code;

    private String title;

    @JsonProperty("title_kh")
    private String titleKh;

    private String description;

    @JsonProperty("description_kh")
    private String descriptionKh;

    @JsonProperty("icon_url")
    private String iconUrl;

    private String status;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}