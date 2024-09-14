package com.peacock.api.recuritment.controller.dto

import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.data.domain.Pageable

data class SearchRecruitmentRequest(
    val type: List<RecruitmentType> = emptyList(),
    val keyword: String = "",
    val positionIds: List<PositionId> = emptyList(),
    val skillIds: List<SkillId> = emptyList(),
) {
    fun toCondition(pageable: Pageable): RecruitmentSearchCondition =
        RecruitmentSearchCondition(
            type = type,
            keyword = keyword,
            positionIds = positionIds,
            skillIds = skillIds,
            pageable = pageable,
        )
}
