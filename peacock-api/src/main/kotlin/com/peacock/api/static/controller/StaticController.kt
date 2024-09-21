package com.peacock.api.static.controller

import com.peacock.core.domain.position.PositionRepository
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.skill.SkillRepository
import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/static")
class StaticController(
    private val positionRepository: PositionRepository,
    private val skillRepository: SkillRepository,
) {
    data class StaticResponse(
        val positions: List<Position>,
        val skills: List<Skill>,
    ) {
        data class Position(
            val id: PositionId,
            val name: String,
        )

        data class Skill(
            val id: SkillId,
            val name: String,
        )
    }

    @GetMapping
    @Cacheable("static", sync = true, key = "'static'")
    fun getStatic(): StaticResponse {
        val positions = positionRepository.findByOrderByIdAsc()

        println("cache miss")
        return StaticResponse(
            positions =
                positions.map { position ->
                    StaticResponse.Position(
                        id = position.id,
                        name = position.name,
                    )
                },
            skills =
                skillRepository.findByOrderByIdAsc().map { skill ->
                    StaticResponse.Skill(
                        id = skill.id,
                        name = skill.name,
                    )
                },
        )
    }
}
