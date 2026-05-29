package com.financial.milestone_tracker.service;

import com.financial.milestone_tracker.constant.ApplicationCode;
import com.financial.milestone_tracker.dto.request.UMAListingFilterRequestDto;
import com.financial.milestone_tracker.dto.response.PaginationResponseDto;
import com.financial.milestone_tracker.dto.response.UMAListingResponse;
import com.financial.milestone_tracker.model.MilestoneUserAccess;
import com.financial.milestone_tracker.repository.UserMilestoneAccessRepository;
import com.financial.milestone_tracker.repository.specification.UMASpecification;
import com.financial.milestone_tracker.util.ResponseBuilderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMilestoneAccessService {

    private final UserMilestoneAccessRepository  userMilestoneAccessRepository;

    public ResponseBuilderUtils<Void> register(Long userId, HttpServletRequest request) {
        MilestoneUserAccess newMilestoneUserAccess = new MilestoneUserAccess();
        newMilestoneUserAccess.setUserId(userId);
        newMilestoneUserAccess.setIsEnabled(false);

        userMilestoneAccessRepository.save(newMilestoneUserAccess);

        return new ResponseBuilderUtils<>(ApplicationCode.HTTP_200, ApplicationCode.CREATED, null);

    }

    public ResponseBuilderUtils<Void> updateStatus(Long userId, HttpServletRequest request) {
        MilestoneUserAccess milestoneUserAccess = userMilestoneAccessRepository.findById(userId).orElse(null);
        if(milestoneUserAccess == null) {
            return new ResponseBuilderUtils<>(
                    ApplicationCode.DBE_001,
                    ApplicationCode.DBE_001_MSG,
                    null
            );
        }
        if(milestoneUserAccess.getIsEnabled()) {
            milestoneUserAccess.setIsEnabled(false);
        } else {
            milestoneUserAccess.setEnabledAt(LocalDateTime.now());
            milestoneUserAccess.setIsEnabled(true);
        }

        userMilestoneAccessRepository.save(milestoneUserAccess);

        return new ResponseBuilderUtils<>(ApplicationCode.HTTP_200, ApplicationCode.UPDATED, null);
    }

    public ResponseBuilderUtils<PaginationResponseDto<UMAListingResponse>> pagination(

            UMAListingFilterRequestDto filterRequest,
            HttpServletRequest httpServletRequest
    ) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(
                        filterRequest.getSortDirection()
                ),
                filterRequest.getSortBy()
        );

        Pageable pageable = PageRequest.of(
                filterRequest.getPage(),
                filterRequest.getSize(),
                sort
        );

        Page<MilestoneUserAccess> umaPage =
                userMilestoneAccessRepository.findAll(
                        UMASpecification.filter(filterRequest),
                        pageable
                );

        List<UMAListingResponse> responses =
                umaPage.getContent()
                        .stream()
                        .map(uma -> {

                            UMAListingResponse response =
                                    new UMAListingResponse();

                            response.setUserId(
                                    uma.getUserId()
                            );

                            response.setIsEnabled(
                                    uma.getIsEnabled()
                            );

                            response.setEnabledAt(
                                    uma.getEnabledAt()
                            );

                            response.setCreatedAt(
                                    uma.getCreatedAt()
                            );

                            return response;
                        })
                        .toList();

        PaginationResponseDto<UMAListingResponse> response =
                PaginationResponseDto
                        .<UMAListingResponse>builder()
                        .contents(responses)
                        .page(umaPage.getNumber())
                        .size(umaPage.getSize())
                        .totalElements(
                                umaPage.getTotalElements()
                        )
                        .totalPages(
                                umaPage.getTotalPages()
                        )
                        .hasNext(
                                umaPage.hasNext()
                        )
                        .hasPrevious(
                                umaPage.hasPrevious()
                        )
                        .build();

        return new ResponseBuilderUtils<>(
                ApplicationCode.HTTP_200,
                ApplicationCode.SUCCESS,
                response
        );
    }
}
