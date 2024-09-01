package com.peacock.api.recuritment.repository.dto

import com.peacock.core.domain.recruitment.vo.RecruitmentType
import java.time.LocalDateTime

data class RecruitmentSearchResult(
    val id: Long,
    val createdAt: LocalDateTime,
    val type: RecruitmentType,
    val title: String,
    val endedAt: LocalDateTime?,
    val positions: Array<Long>,
)
