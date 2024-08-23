package com.peacock.api.recuritment.controller.dto

import com.peacock.api.recuritment.service.dto.CreateRecruitmentCommand
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import com.peacock.core.domain.skill.vo.SkillId
import com.peacock.core.domain.vo.PositiveInt
import java.time.LocalDateTime

data class CreateRecruitmentRequest(
    val title: String,
    val content: String,
    val purpose: RecruitmentPurpose,
    val method: Method,
    val processType: RecruitmentProcessType,
    val endedAt: LocalDateTime?,
    val duration: String,
    val interval: Interval,
    val positionGroup: List<PositionGroup>,
) {
    data class Method(
        val type: RecruitmentMethod.Type,
        val contact: String,
    )

    data class Interval(
        val type: RecruitmentInterval.Type,
        val frequency: Int,
    )

    data class PositionGroup(
        val count: Int,
        val positions: List<PositionId>,
        val skills: List<SkillId>,
    )

    fun toCommand() =
        CreateRecruitmentCommand(
            title = title,
            content = content,
            purpose = purpose,
            method = RecruitmentMethod(type = method.type, contact = method.contact),
            processType = processType,
            endedAt = endedAt,
            duration = duration,
            interval = RecruitmentInterval(type = interval.type, frequency = PositiveInt(interval.frequency)),
            positionGroup =
                positionGroup.map {
                    CreateRecruitmentCommand.PositionGroup(
                        count = PositiveInt(it.count),
                        positions = it.positions,
                        skills = it.skills,
                    )
                },
        )
}
