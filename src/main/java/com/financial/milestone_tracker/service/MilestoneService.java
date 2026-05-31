package com.financial.milestone_tracker.service;

import com.financial.milestone_tracker.constant.ApplicationCode;
import com.financial.milestone_tracker.dto.request.CreateMilestoneRequestDto;
import com.financial.milestone_tracker.dto.request.MilestoneListingFilterRequestDto;
import com.financial.milestone_tracker.dto.request.UpdateMilestoneRequestDto;
import com.financial.milestone_tracker.dto.response.MilestoneListingResponse;
import com.financial.milestone_tracker.dto.response.MilestoneResponse;
import com.financial.milestone_tracker.dto.response.PaginationResponseDto;
import com.financial.milestone_tracker.exception.DatabaseException;
import com.financial.milestone_tracker.mapper.MilestoneMapper;
import com.financial.milestone_tracker.model.Milestone;
import com.financial.milestone_tracker.model.MilestoneCondition;
import com.financial.milestone_tracker.repository.MilestoneConditionRepository;
import com.financial.milestone_tracker.repository.MilestoneRepository;
import com.financial.milestone_tracker.repository.specification.MilestoneSpecification;
import com.financial.milestone_tracker.util.ResponseBuilderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MilestoneService {

    private final MilestoneRepository  milestoneRepository;
    private final MilestoneMapper milestoneMapper;
    private final MilestoneConditionRepository milestoneConditionRepository;

    private Milestone fetch(Integer milestoneId) {
        return milestoneRepository.findById(milestoneId).orElse(null);
    }

    public ResponseBuilderUtils<MilestoneResponse> queryMilestone(Integer milestoneId) {
        Milestone milestone = fetch(milestoneId);
        if( milestone == null )
            return new ResponseBuilderUtils<>(
                    ApplicationCode.DBE_001,
                    ApplicationCode.DBE_001_MSG,
                    null
            );

        return new ResponseBuilderUtils<>(
                ApplicationCode.HTTP_200,
                ApplicationCode.CREATED,
                milestone
        );
    }

    @Transactional
    public ResponseBuilderUtils<Void> createMilestone(CreateMilestoneRequestDto createMilestoneRequestDto, HttpServletRequest request) {

        if (createMilestoneRequestDto.getConditions().isEmpty())
            throw new RuntimeException("Condition must not be empty");

        // CREATE MILESTONE
        Milestone createNewMilestone = new Milestone();
        createNewMilestone.setCode(createMilestoneRequestDto.getCode());
        createNewMilestone.setTitle(createMilestoneRequestDto.getTitle());
        createNewMilestone.setTitleKh(createMilestoneRequestDto.getTitleKh());
        createNewMilestone.setDescription(createMilestoneRequestDto.getDescription());
        createNewMilestone.setDescriptionKh(createMilestoneRequestDto.getDescriptionKh());
        createNewMilestone.setIconUrl(createMilestoneRequestDto.getIconUrl());
        createNewMilestone.setStatus(createMilestoneRequestDto.getStatus());

        Milestone savedMilestone = milestoneRepository.save(createNewMilestone);

        // CREATE CONDITIONS
        List<MilestoneCondition> milestoneConditions =
                createMilestoneRequestDto.getConditions()
                        .stream()
                        .map(conditionDto -> {

                            MilestoneCondition milestoneCondition = new MilestoneCondition();
                            milestoneCondition.setMilestone(savedMilestone);
                            milestoneCondition.setMetricType(conditionDto.getMetricType());
                            milestoneCondition.setOperator(conditionDto.getOperator());
                            milestoneCondition.setTargetValue(conditionDto.getTargetValue());
                            milestoneCondition.setSequenceNo(conditionDto.getSequenceNo());
                            milestoneCondition.setStatus(conditionDto.getStatus());
                            return milestoneCondition;
                        })
                        .toList();

        milestoneConditionRepository.saveAll(milestoneConditions);

        return new ResponseBuilderUtils<>(ApplicationCode.HTTP_200, ApplicationCode.CREATED, null);
    }

    @Transactional
    public ResponseBuilderUtils<Void> deleteMilestone(Integer milestoneId, HttpServletRequest request) {
        Milestone milestone = fetch(milestoneId);
        if( milestone == null )
            return new ResponseBuilderUtils<>(
                    ApplicationCode.DBE_001,
                    ApplicationCode.DBE_001_MSG,
                    null
            );
        milestoneRepository.delete(milestone);
        return new ResponseBuilderUtils<>(
                ApplicationCode.HTTP_200,
                ApplicationCode.DELETED,
                null
        );
    }

    public ResponseBuilderUtils<PaginationResponseDto<MilestoneListingResponse>> listingMilestone(MilestoneListingFilterRequestDto request, HttpServletRequest httpServletRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(request.getSortDirection()), request.getSortBy());

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                sort
        );

        Page<Milestone> milestonePage = milestoneRepository.findAll(
                        MilestoneSpecification.filter(request),
                        pageable
        );

        List<MilestoneListingResponse> responses =
                milestonePage.getContent()
                        .stream()
                        .map(milestone -> {
                            MilestoneListingResponse response = new MilestoneListingResponse();
                            response.setId(milestone.getId());
                            response.setCode(milestone.getCode());
                            response.setStatus(milestone.getStatus());
                            response.setCreatedAt(milestone.getCreatedAt());
                            response.setDescription(milestone.getDescription());
                            response.setDescriptionKh(milestone.getDescriptionKh());
                            response.setTitle(milestone.getTitle());
                            response.setTitleKh(milestone.getTitleKh());
                            response.setIconUrl(milestone.getIconUrl());

                            return response;
                        })
                        .toList();

        PaginationResponseDto<MilestoneListingResponse> response =
                PaginationResponseDto
                        .<MilestoneListingResponse>builder()
                        .contents(responses)
                        .page(milestonePage.getNumber())
                        .size(milestonePage.getSize())
                        .totalElements(milestonePage.getTotalElements())
                        .totalPages(milestonePage.getTotalPages())
                        .hasNext(milestonePage.hasNext())
                        .hasPrevious(milestonePage.hasPrevious())
                        .build();

        return new ResponseBuilderUtils<>(
                ApplicationCode.HTTP_200,
                ApplicationCode.SUCCESS,
                response
        );
    }

    @Transactional
    public ResponseBuilderUtils<?> updateMilestone(UpdateMilestoneRequestDto request, HttpServletRequest httpServletRequest) {

        Milestone milestone = milestoneRepository
                .findById(request.getMilestoneId())
                .orElseThrow(() ->
                        new DatabaseException(
                                ApplicationCode.DBE_001,
                                ApplicationCode.DBE_001_MSG
                        )
                );

        // UPDATE MILESTONE
        if(request.getCode() != null)
            milestone.setCode(request.getCode());

        if(request.getTitle() != null)
            milestone.setTitle(request.getTitle());

        if (request.getDescription() != null)
            milestone.setDescription(request.getDescription());

        if(request.getTitleKh() != null)
            milestone.setTitleKh(request.getTitleKh());

        if(request.getDescriptionKh() != null)
            milestone.setDescriptionKh(request.getDescriptionKh());

        if(request.getIconUrl() != null)
            milestone.setIconUrl(request.getIconUrl());

        if(request.getStatus() != null)
            milestone.setStatus(request.getStatus());

        milestoneRepository.save(milestone);

        if (request.getConditions() != null && !request.getConditions().isEmpty()) {

            // DELETE OLD CONDITIONS
            milestoneConditionRepository.deleteByMilestone(milestone);

            // CREATE NEW CONDITIONS
            List<MilestoneCondition> milestoneConditions =
                    request.getConditions()
                            .stream()
                            .map(conditionDto -> {

                                MilestoneCondition condition =
                                        new MilestoneCondition();

                                condition.setMilestone(milestone);

                                if (conditionDto.getMetricType() != null)
                                    condition.setMetricType(
                                            conditionDto.getMetricType()
                                    );

                                if (conditionDto.getOperator() != null)
                                    condition.setOperator(
                                            conditionDto.getOperator()
                                    );

                                if (conditionDto.getTargetValue() != null)
                                    condition.setTargetValue(
                                            conditionDto.getTargetValue()
                                    );

                                if (conditionDto.getSequenceNo() != null)
                                    condition.setSequenceNo(
                                            conditionDto.getSequenceNo()
                                    );

                                if (conditionDto.getStatus() != null)
                                    condition.setStatus(
                                            conditionDto.getStatus()
                                    );

                                return condition;
                            })
                            .toList();

            milestoneConditionRepository.saveAll(milestoneConditions);
        }

        return new ResponseBuilderUtils<>(
                ApplicationCode.HTTP_200,
                ApplicationCode.SUCCESS,
                null
        );
    }
}
