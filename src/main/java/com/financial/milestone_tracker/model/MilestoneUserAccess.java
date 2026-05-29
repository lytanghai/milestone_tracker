package com.financial.milestone_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_milestone_access")
@Data
public class MilestoneUserAccess {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

    @Column(name = "enabled_at")
    private LocalDateTime enabledAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}