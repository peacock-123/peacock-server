package com.peacock.core.domain.recruitment

import com.peacock.core.domain.vo.PositiveInt
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class RecruitmentPositionGroup(
    val count: PositiveInt,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    @MappedCollection(idColumn = "recruitment_position_group_id", keyColumn = "sequence")
    val positions: List<RecruitmentPosition>,
    @MappedCollection(idColumn = "recruitment_position_group_id", keyColumn = "sequence")
    val skills: List<RecruitmentSkill>,
) {
    init {
        require(positions.isNotEmpty()) { "At least one position is required" }
    }
}
