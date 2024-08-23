package com.peacock.api.recuritment.controller

import com.peacock.api.recuritment.controller.dto.CreateRecruitmentRequest
import com.peacock.api.recuritment.controller.dto.CreateRecruitmentResponse
import com.peacock.api.recuritment.service.RecruitmentService
import com.peacock.api.session.SessionId
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
}
