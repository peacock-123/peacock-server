package com.peacock.core.domain.recruitment

import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class Recruitment(
    @Id
    val id: Long,
    val title: String,
    val content: String,
    val purpose: RecruitmentPurpose,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val recruitmentMethod: RecruitmentMethod,
    val processType: RecruitmentProcessType,
    val endedAt: LocalDateTime?,
    val duration: String,
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    val interval: RecruitmentInterval,
)
