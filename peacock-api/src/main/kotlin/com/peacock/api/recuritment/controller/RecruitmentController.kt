package com.peacock.api.recuritment.controller

import com.peacock.api.recuritment.controller.dto.CreateRecruitmentRequest
import com.peacock.api.recuritment.service.RecruitmentService
import org.springframework.http.ResponseEntity
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
    ): ResponseEntity<CreateRecruitmentRequest> = ResponseEntity.ok().body(request)
}
