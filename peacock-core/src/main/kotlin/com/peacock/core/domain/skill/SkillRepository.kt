package com.peacock.core.domain.skill

import org.springframework.data.repository.ListCrudRepository

interface SkillRepository : ListCrudRepository<Skill, Long> {
    fun findByOrderByIdAsc(): List<Skill>
}
