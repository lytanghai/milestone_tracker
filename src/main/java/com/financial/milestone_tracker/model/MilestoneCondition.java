package com.financial.milestone_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "milestone_condition")
@Getter
@Setter
public class MilestoneCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    @JsonBackReference
    private Milestone milestone;

    @Column(name = "metric_type", nullable = false)
    private String metricType;

    @Column(name = "operator", nullable = false)
    private String operator;

    @Column(name = "target_value", nullable = false)
    private BigDecimal targetValue;

    @Column(name = "sequence_no")
    private Integer sequenceNo;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}