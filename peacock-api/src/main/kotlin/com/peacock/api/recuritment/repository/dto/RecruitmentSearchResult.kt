package com.peacock.api.recuritment.repository.dto

import java.time.OffsetDateTime

data class RecruitmentSearchResult(
    val id: Long,
    val createdAt: OffsetDateTime,
    val type: String,
    val title: String,
    val endedAt: OffsetDateTime?,
    val positions: Array<Long>,
)
