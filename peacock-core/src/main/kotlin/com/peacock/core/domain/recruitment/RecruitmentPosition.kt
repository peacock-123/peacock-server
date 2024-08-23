package com.peacock.core.domain.recruitment

import com.peacock.core.domain.position.vo.PositionId
import org.springframework.data.relational.core.mapping.Table

@Table
data class RecruitmentPosition(
    val positionId: PositionId,
)
