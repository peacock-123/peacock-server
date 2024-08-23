package com.peacock.core.domain.recruitment

import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class RecruitmentSkill(
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val skillId: SkillId,
)
