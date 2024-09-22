package com.peacock.api.recuritment.controller.dto

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.Recruitment
import com.peacock.core.domain.recruitment.RecruitmentPosition
import com.peacock.core.domain.recruitment.RecruitmentSkill
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentPositionGroupId
import com.peacock.core.domain.recruitment.vo.RecruitmentProcessType
import com.peacock.core.domain.recruitment.vo.RecruitmentPurpose
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.skill.vo.SkillId
import com.peacock.core.domain.vo.NonNegativeLong
import com.peacock.core.domain.vo.PositiveInt
import java.time.LocalDateTime

data class GetRecruitmentResponse(
    val id: RecruitmentId,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
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
    val authorId: AccountId,
    val viewCount: NonNegativeLong,
) {
    data class PositionGroup(
        val id: RecruitmentPositionGroupId,
        val count: PositiveInt,
        val positions: List<PositionId>,
        val skills: List<SkillId>,
    )

    companion object {
        fun of(recruitment: Recruitment): GetRecruitmentResponse =
            GetRecruitmentResponse(
                id = recruitment.id,
                createdAt = recruitment.createdAt,
                updatedAt = recruitment.updatedAt,
                type = recruitment.type,
                title = recruitment.title,
                content = recruitment.content,
                purpose = recruitment.purpose,
                method = recruitment.method,
                processType = recruitment.processType,
                endedAt = recruitment.endedAt,
                duration = recruitment.duration,
                interval = recruitment.interval,
                positionGroup =
                    recruitment.positionGroup.map {
                        PositionGroup(
                            id = it.id,
                            count = PositiveInt(it.positions.size),
                            positions = it.positions.map(RecruitmentPosition::positionId),
                            skills = it.skills.map(RecruitmentSkill::skillId),
                        )
                    },
                authorId = recruitment.authorId,
                viewCount = recruitment.viewCount,
            )
    }
}
