package com.peacock.api.recuritment.service.dto

import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.Recruitment
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import com.peacock.core.domain.skill.vo.SkillId
import com.peacock.core.domain.vo.PositiveInt
import java.time.LocalDateTime

data class CreateRecruitmentCommand(
    val title: String,
    val content: String,
    val purpose: RecruitmentPurpose,
    val method: RecruitmentMethod,
    val processType: RecruitmentProcessType,
    val endedAt: LocalDateTime?,
    val duration: String,
    val interval: RecruitmentInterval,
    val positionGroup: List<PositionGroup>,
) {
    data class PositionGroup(
        val count: PositiveInt,
        val positions: List<PositionId>,
        val skills: List<SkillId>,
    )

    fun toEntity(): Recruitment {
        TODO("Not yet implemented")
    }
}
