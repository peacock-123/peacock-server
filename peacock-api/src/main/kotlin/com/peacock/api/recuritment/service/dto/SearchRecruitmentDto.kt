package com.peacock.api.recuritment.service.dto

import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import java.time.LocalDateTime

data class SearchRecruitmentDto(
    val id: RecruitmentId,
    val createdAt: LocalDateTime,
    val type: RecruitmentType,
    val title: String,
    val endedAt: LocalDateTime?,
    val positions: List<String>,
    val author: String,
    val viewCount: Long,
    val totalCapacity: Long,
)
