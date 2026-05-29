package com.financial.milestone_tracker.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MilestoneListingFilterRequestDto {

    private String code;

    private String title;

    private String status;

    private String sortBy = "createdAt";

    private String sortDirection = "DESC";

    private Integer page = 0;

    private Integer size = 10;
}