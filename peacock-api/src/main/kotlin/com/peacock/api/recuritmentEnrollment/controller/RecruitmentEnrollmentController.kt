package com.peacock.api.recuritmentEnrollment.controller

import com.peacock.api.recuritmentEnrollment.controller.dto.EnrollRecruitmentRequest
import com.peacock.api.recuritmentEnrollment.service.RecruitmentEnrollmentService
import com.peacock.api.session.SessionId
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/recruitment-enrollments")
class RecruitmentEnrollmentController(
    private val recruitmentEnrollmentService: RecruitmentEnrollmentService,
) {
    @PostMapping
    fun enroll(
        @RequestBody request: EnrollRecruitmentRequest,
        @AuthenticationPrincipal sessionId: SessionId,
    ): ResponseEntity<Unit> {
        recruitmentEnrollmentService.enroll(request.toCommand(sessionId))

        return ResponseEntity.ok().build()
    }
}
