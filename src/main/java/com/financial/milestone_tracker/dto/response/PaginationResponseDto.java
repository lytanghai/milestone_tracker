package com.financial.milestone_tracker.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationResponseDto<T> {

    private List<T> contents;

    private Integer page;

    private Integer size;

    private Long totalElements;

    private Integer totalPages;

    private Boolean hasNext;

    private Boolean hasPrevious;
}