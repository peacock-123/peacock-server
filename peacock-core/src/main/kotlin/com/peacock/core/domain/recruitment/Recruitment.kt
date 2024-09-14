package com.peacock.core.domain.recruitment

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.vo.NonNegativeLong
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
    val type: RecruitmentType,
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
    val viewCount: NonNegativeLong = NonNegativeLong(0),
) {
    init {
        require(title.isNotBlank()) { "제목은 필수입니다" }
        require(duration.isNotBlank()) { "모집 기간은 필수입니다" }
        require(positionGroup.isNotEmpty()) { "포지션 그룹은 필수입니다" }
    }
}
