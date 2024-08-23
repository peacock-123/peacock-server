package com.peacock.core.domain.recruitment

import com.peacock.core.domain.position.vo.PositionId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class RecruitmentPosition(
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val positionId: PositionId,
)
