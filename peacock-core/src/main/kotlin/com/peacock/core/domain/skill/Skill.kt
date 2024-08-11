package com.peacock.core.domain.skill

import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Skill(
    @Id
    val id: SkillId,
    val name: String,
)
