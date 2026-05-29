CREATE TABLE milestone (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(100) NOT NULL,
    title_kh VARCHAR(255),
    description TEXT,
    description_kh TEXT,
    icon_url VARCHAR(500),
    status VARCHAR(10) NOT NULL DEFAULT 'ACTIVE'
        CHECK (status IN ('ACTIVE', 'INACTIVE')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE milestone_condition (
    id BIGSERIAL PRIMARY KEY,
    milestone_id BIGINT NOT NULL,
    metric_type VARCHAR(30) NOT NULL,
    operator VARCHAR(10) NOT NULL,
    target_value NUMERIC(20,2) NOT NULL,
    sequence_no INT NOT NULL DEFAULT 1,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_milestone_condition_milestone
        FOREIGN KEY (milestone_id)
        REFERENCES milestone(id)
        ON DELETE CASCADE
);

CREATE TABLE user_milestone_access (
    user_id BIGINT PRIMARY KEY,
    is_enabled BOOLEAN NOT NULL DEFAULT TRUE,
    enabled_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_milestone_progress (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    milestone_id BIGINT NOT NULL,
    current_value NUMERIC(20,2) NOT NULL DEFAULT 0,
    progress_percentage NUMERIC(5,2) NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    completed_at TIMESTAMP,
    last_event_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_user_milestone
        UNIQUE (user_id, milestone_id),
    CONSTRAINT fk_user_milestone_progress_milestone
        FOREIGN KEY (milestone_id)
        REFERENCES milestone(id)
        ON DELETE CASCADE
);

-- =========================================================
-- INDEXES
-- =========================================================

CREATE INDEX idx_milestone_status
    ON milestone(status);

CREATE INDEX idx_milestone_condition_milestone
    ON milestone_condition(milestone_id);

CREATE INDEX idx_user_milestone_progress_user
    ON user_milestone_progress(user_id);

CREATE INDEX idx_user_milestone_progress_status
    ON user_milestone_progress(status);

CREATE INDEX idx_user_milestone_progress_milestone
    ON user_milestone_progress(milestone_id);