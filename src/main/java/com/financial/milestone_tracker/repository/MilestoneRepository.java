package com.financial.milestone_tracker.repository;

import com.financial.milestone_tracker.model.Milestone;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer>, JpaSpecificationExecutor<Milestone> {

    @EntityGraph(attributePaths = {"conditions"})
    Optional<Milestone> findDetailById(Integer id);



}
