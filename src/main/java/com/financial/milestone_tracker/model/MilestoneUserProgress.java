package com.financial.milestone_tracker.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(
        name = "milestone_user_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_milestone",
                        columnNames = {"user_id", "milestone_id"}
                )
        }
)
@Data
public class MilestoneUserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id", nullable = false)
    private Milestone milestone;

    @Column(name = "current_value")
    private BigDecimal currentValue = BigDecimal.ZERO;

    @Column(name = "progress_percentage")
    private BigDecimal progressPercentage = BigDecimal.ZERO;

    @Column(name = "status")
    private String status;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_event_at")
    private LocalDateTime lastEventAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}