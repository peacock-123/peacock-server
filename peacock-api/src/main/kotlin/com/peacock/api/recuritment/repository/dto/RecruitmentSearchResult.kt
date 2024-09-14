package com.peacock.api.recuritment.repository.dto

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import java.math.BigDecimal
import java.time.LocalDateTime

data class RecruitmentSearchResult(
    private val rawId: Long,
    val createdAt: LocalDateTime,
    val type: RecruitmentType,
    val title: String,
    val endedAt: LocalDateTime?,
    private val rawAuthorId: Long,
    private val rawPositions: Array<Long>,
    val totalCapacity: BigDecimal,
) {
    val id: RecruitmentId = RecruitmentId(rawId)
    val authorId: AccountId = AccountId(rawAuthorId)
    val positions = rawPositions.map { PositionId(it) }
}
