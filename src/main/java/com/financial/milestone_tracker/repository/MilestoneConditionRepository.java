package com.financial.milestone_tracker.repository;

import com.financial.milestone_tracker.model.Milestone;
import com.financial.milestone_tracker.model.MilestoneCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneConditionRepository extends JpaRepository<MilestoneCondition, Long> {
    void deleteByMilestone(Milestone milestone);
}
