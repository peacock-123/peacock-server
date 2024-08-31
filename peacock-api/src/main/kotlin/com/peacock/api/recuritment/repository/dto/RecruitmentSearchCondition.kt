package com.peacock.api.recuritment.repository.dto

import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.skill.vo.SkillId
import org.springframework.data.domain.Pageable

data class RecruitmentSearchCondition(
    val type: List<RecruitmentType>,
    val keyword: String,
    val positionIds: List<PositionId>,
    val skillIds: List<SkillId>,
    val pageable: Pageable,
)
