package com.peacock.core.domain.recruitment

import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.data.relational.core.mapping.Table

@Table
data class RecruitmentSkill(
    val skillId: SkillId,
)
