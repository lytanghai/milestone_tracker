package com.financial.milestone_tracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "milestone")
@Getter
@Setter
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "title_kh")
    private String titleKh;

    @Column(name = "description")
    private String description;

    @Column(name = "description_kh")
    private String descriptionKh;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "milestone", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<MilestoneCondition> conditions;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;
}