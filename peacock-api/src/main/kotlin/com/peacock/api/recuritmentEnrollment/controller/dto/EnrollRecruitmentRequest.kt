package com.peacock.api.recuritmentEnrollment.controller.dto

import com.peacock.api.recuritmentEnrollment.service.dto.EnrollRecruitmentCommand
import com.peacock.api.session.SessionId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentContact

data class EnrollRecruitmentRequest(
    val recruitmentId: RecruitmentId,
    val positionId: PositionId,
    val contact: RecruitmentEnrollmentContact,
    val resumeLink: String,
) {
    fun toCommand(sessionId: SessionId) =
        EnrollRecruitmentCommand(
            recruitmentId = recruitmentId,
            positionId = positionId,
            accountId = sessionId.toAccountId(),
            contact = contact,
            resumeLink = resumeLink,
        )
}
