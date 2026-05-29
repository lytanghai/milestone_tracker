package com.financial.milestone_tracker.controller;

import com.financial.milestone_tracker.dto.request.UMAListingFilterRequestDto;
import com.financial.milestone_tracker.dto.response.PaginationResponseDto;
import com.financial.milestone_tracker.dto.response.UMAListingResponse;
import com.financial.milestone_tracker.service.UserMilestoneAccessService;
import com.financial.milestone_tracker.util.ResponseBuilderUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uma")
@AllArgsConstructor
public class UserMilestoneAccessController {

    private final UserMilestoneAccessService userMilestoneAccessService;

    @PostMapping("/register/{userId}")
    public ResponseEntity<ResponseBuilderUtils> register(@PathVariable Long userId, HttpServletRequest request) {
        return new ResponseEntity<>(userMilestoneAccessService.register(userId, request), HttpStatus.OK);
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<ResponseBuilderUtils> updateStatus(@PathVariable Long userId, HttpServletRequest request) {
        return new ResponseEntity<>(userMilestoneAccessService.updateStatus(userId, request), HttpStatus.OK);
    }

    @PostMapping("/fetch")
    public ResponseEntity<ResponseBuilderUtils<PaginationResponseDto<UMAListingResponse>>> fetch(@RequestBody UMAListingFilterRequestDto payload, HttpServletRequest request) {
        return new ResponseEntity<>(userMilestoneAccessService.pagination(payload, request), HttpStatus.OK);
    }
}
