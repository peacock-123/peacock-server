package com.peacock.api.recuritmentEnrollment.service.dto

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentContact

data class EnrollRecruitmentCommand(
    val recruitmentId: RecruitmentId,
    val accountId: AccountId,
    val positionId: PositionId,
    val contact: RecruitmentEnrollmentContact,
    val resumeLink: String,
)
