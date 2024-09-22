package com.peacock.api.recuritment.controller

import com.peacock.api.recuritment.controller.dto.CreateRecruitmentRequest
import com.peacock.api.recuritment.controller.dto.CreateRecruitmentResponse
import com.peacock.api.recuritment.controller.dto.GetRecruitmentResponse
import com.peacock.api.recuritment.controller.dto.SearchRecruitmentRequest
import com.peacock.api.recuritment.service.RecruitmentService
import com.peacock.api.recuritment.service.dto.SearchRecruitmentDto
import com.peacock.api.session.SessionId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/recruitments")
class RecruitmentController(
    private val recruitmentService: RecruitmentService,
) {
    @PostMapping
    fun createRecruitment(
        @RequestBody request: CreateRecruitmentRequest,
        @AuthenticationPrincipal sessionId: SessionId,
    ): ResponseEntity<CreateRecruitmentResponse> {
        val createdRecruitmentId = recruitmentService.create(request.toCommand(sessionId))

        return ResponseEntity.ok(CreateRecruitmentResponse(createdRecruitmentId))
    }

    @GetMapping("/search")
    fun searchRecruitments(
        request: SearchRecruitmentRequest,
        pageable: Pageable,
    ): ResponseEntity<Page<SearchRecruitmentDto>> =
        ResponseEntity.ok(
            recruitmentService.search(request.toCondition(pageable)),
        )

    @GetMapping("/{recruitmentId}")
    fun getRecruitment(
        @PathVariable recruitmentId: RecruitmentId,
    ): ResponseEntity<GetRecruitmentResponse> =
        ResponseEntity.ok(
            GetRecruitmentResponse.of(
                recruitmentService.get(recruitmentId),
            ),
        )
}
