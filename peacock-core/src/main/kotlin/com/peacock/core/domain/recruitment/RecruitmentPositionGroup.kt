package com.peacock.core.domain.recruitment

import com.peacock.core.domain.vo.PositiveInt
import org.springframework.data.relational.core.mapping.Table

@Table
data class RecruitmentPositionGroup(
    val count: PositiveInt,
)
