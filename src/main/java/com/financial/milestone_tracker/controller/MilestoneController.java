package com.financial.milestone_tracker.controller;

import com.financial.milestone_tracker.dto.request.CreateMilestoneRequestDto;
import com.financial.milestone_tracker.dto.request.MilestoneListingFilterRequestDto;
import com.financial.milestone_tracker.dto.request.UpdateMilestoneRequestDto;
import com.financial.milestone_tracker.dto.response.MilestoneListingResponse;
import com.financial.milestone_tracker.dto.response.PaginationResponseDto;
import com.financial.milestone_tracker.service.MilestoneService;
import com.financial.milestone_tracker.util.ResponseBuilderUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/milestone")
public class MilestoneController {

    private final MilestoneService milestoneService;

    @PostMapping("/create")
    public ResponseEntity<ResponseBuilderUtils> createMilestone(@RequestBody CreateMilestoneRequestDto configuration, HttpServletRequest request) {
        return new ResponseEntity<>(milestoneService.createMilestone(configuration, request), HttpStatus.OK);
    }

    @GetMapping("/query/{milestoneId}")
    public ResponseEntity<ResponseBuilderUtils> queryMilestone(@PathVariable Integer milestoneId, HttpServletRequest request) {
        return new ResponseEntity<>(milestoneService.queryMilestone(milestoneId), HttpStatus.OK);
    }

    @PostMapping("/delete/{milestoneId}")
    public ResponseEntity<ResponseBuilderUtils> deleteMilestone(@PathVariable Integer milestoneId, HttpServletRequest request) {
        return new ResponseEntity<>(milestoneService.deleteMilestone(milestoneId, request), HttpStatus.OK);
    }

    @PostMapping("/fetch")
    public ResponseEntity<ResponseBuilderUtils<PaginationResponseDto<MilestoneListingResponse>>> fetch(@RequestBody MilestoneListingFilterRequestDto payload, HttpServletRequest request) {
        return new ResponseEntity<>(milestoneService.listingMilestone(payload, request), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMilestone( @Valid @RequestBody UpdateMilestoneRequestDto request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(milestoneService.updateMilestone(request, httpServletRequest));
    }

}
