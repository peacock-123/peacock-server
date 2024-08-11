package com.peacock.core.domain.position

import com.peacock.core.domain.position.vo.PositionId
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Position(
    @Id
    val id: PositionId,
    val name: String,
)
