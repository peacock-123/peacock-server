package com.peacock.core.domain.recruitment

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class Recruitment(
    @Id
    val id: RecruitmentId = RecruitmentId(0),
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val title: String,
    val content: String,
    val purpose: RecruitmentPurpose,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val method: RecruitmentMethod,
    val processType: RecruitmentProcessType,
    val endedAt: LocalDateTime?,
    val duration: String,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val interval: RecruitmentInterval,
    @MappedCollection(idColumn = "recruitment_id", keyColumn = "sequence")
    val positionGroup: List<RecruitmentPositionGroup>,
    val authorId: AccountId,
)
