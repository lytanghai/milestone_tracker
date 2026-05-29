package com.financial.milestone_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateMilestoneRequestDto {

    @JsonProperty("milestone_id")
    private Integer milestoneId;

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

    private List<UpdateMilestoneConditionRequestDto> conditions;
}