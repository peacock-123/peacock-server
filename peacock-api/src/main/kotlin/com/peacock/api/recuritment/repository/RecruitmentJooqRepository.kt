package com.peacock.api.recuritment.repository

import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchResult
import org.springframework.data.domain.Page

interface RecruitmentJooqRepository {
    fun search(condition: RecruitmentSearchCondition): Page<RecruitmentSearchResult>
}
