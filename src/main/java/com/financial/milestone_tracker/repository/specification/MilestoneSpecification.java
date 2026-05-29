package com.financial.milestone_tracker.repository.specification;

import com.financial.milestone_tracker.dto.request.MilestoneListingFilterRequestDto;
import com.financial.milestone_tracker.model.Milestone;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MilestoneSpecification {

    public static Specification<Milestone> filter(MilestoneListingFilterRequestDto request) {

        return (root, query, cb) -> {

            Predicate predicate = cb.conjunction();

            // code
            if (request.getCode() != null
                    && !request.getCode().isBlank()) {

                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(root.get("code")),
                                "%" + request.getCode().toLowerCase() + "%"
                        )
                );
            }

            // title
            if (request.getTitle() != null
                    && !request.getTitle().isBlank()) {

                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(root.get("title")),
                                "%" + request.getTitle().toLowerCase() + "%"
                        )
                );
            }

            // status
            if (request.getStatus() != null
                    && !request.getStatus().isBlank()) {

                predicate = cb.and(
                        predicate,
                        cb.equal(
                                root.get("status"),
                                request.getStatus()
                        )
                );
            }

            return predicate;
        };
    }
}