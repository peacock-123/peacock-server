package com.peacock.api.recuritment.service.dto

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.Recruitment
import com.peacock.core.domain.recruitment.RecruitmentPosition
import com.peacock.core.domain.recruitment.RecruitmentPositionGroup
import com.peacock.core.domain.recruitment.RecruitmentSkill
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.skill.vo.SkillId
import com.peacock.core.domain.vo.PositiveInt
import java.time.LocalDateTime

data class CreateRecruitmentCommand(
    val authorId: AccountId,
    val type: RecruitmentType,
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

    fun toEntity(): Recruitment =
        Recruitment(
            authorId = authorId,
            type = type,
            title = title,
            content = content,
            purpose = purpose,
            method = method,
            processType = processType,
            endedAt = endedAt,
            duration = duration,
            interval = interval,
            positionGroup =
                positionGroup.map { group ->
                    RecruitmentPositionGroup(
                        count = group.count,
                        positions = group.positions.map { RecruitmentPosition(positionId = it) },
                        skills = group.skills.map { RecruitmentSkill(skillId = it) },
                    )
                },
        )
}
