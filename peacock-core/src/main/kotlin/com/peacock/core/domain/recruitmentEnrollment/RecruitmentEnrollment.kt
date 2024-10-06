package com.peacock.core.domain.recruitmentEnrollment

import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentContact
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class RecruitmentEnrollment(
    @Id
    val id: RecruitmentEnrollmentId,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val positionId: PositionId,
    val contact: RecruitmentEnrollmentContact,
    val resumeLink: String,
)
