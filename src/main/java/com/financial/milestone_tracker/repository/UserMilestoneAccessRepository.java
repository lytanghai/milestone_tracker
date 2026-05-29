package com.financial.milestone_tracker.repository;

import com.financial.milestone_tracker.model.MilestoneUserAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMilestoneAccessRepository extends JpaRepository<MilestoneUserAccess, Long>,
        JpaSpecificationExecutor<MilestoneUserAccess> {
}
