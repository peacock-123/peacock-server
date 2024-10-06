package com.peacock.core.domain.recruitmentEnrollment

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentContact
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class RecruitmentEnrollment(
    @Id
    val id: RecruitmentEnrollmentId = RecruitmentEnrollmentId(0),
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val recruitmentId: RecruitmentId,
    val accountId: AccountId,
    val positionId: PositionId,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val contact: RecruitmentEnrollmentContact,
    val resumeLink: String,
)
