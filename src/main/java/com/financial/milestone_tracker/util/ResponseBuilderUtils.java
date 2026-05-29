package com.financial.milestone_tracker.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseBuilderUtils<R>(
        @JsonProperty("code") String code,
        @JsonProperty("message") String message,
        @JsonProperty("data") Object data) {
}
