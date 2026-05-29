package com.financial.milestone_tracker.mapper;

import com.financial.milestone_tracker.dto.response.MilestoneListingResponse;
import com.financial.milestone_tracker.model.Milestone;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MilestoneMapper {

    MilestoneListingResponse toListingResponse(Milestone milestone);

}
