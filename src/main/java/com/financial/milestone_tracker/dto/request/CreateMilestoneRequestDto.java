package com.financial.milestone_tracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financial.milestone_tracker.constant.ApplicationConstant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateMilestoneRequestDto {

    @NotBlank
    private String code;

    @NotBlank
    private String title;

    @JsonProperty("title_kh")
    private String titleKh;

    private String description;

    @JsonProperty("description_kh")
    private String descriptionKh;

    @JsonProperty("icon_url")
    private String iconUrl;

    private String status = ApplicationConstant.ACTIVE;

    @Valid
    @NotEmpty
    private List<CreateMilestoneConditionRequestDto> conditions;
}