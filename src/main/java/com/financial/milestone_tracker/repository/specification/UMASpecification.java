package com.financial.milestone_tracker.repository.specification;

import com.financial.milestone_tracker.dto.request.UMAListingFilterRequestDto;
import com.financial.milestone_tracker.model.MilestoneUserAccess;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UMASpecification {

    public static Specification<MilestoneUserAccess> filter(
            UMAListingFilterRequestDto request
    ) {

        return (root, query, cb) -> {

            Predicate predicate = cb.conjunction();

            if (request.getUserId() != null) {

                predicate = cb.and(
                        predicate,
                        cb.equal(
                                root.get("userId"),
                                request.getUserId()
                        )
                );
            }
            return predicate;
        };
    }
}