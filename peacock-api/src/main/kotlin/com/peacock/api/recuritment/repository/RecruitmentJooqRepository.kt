package com.peacock.api.recuritment.repository

import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchResult

interface RecruitmentJooqRepository {
    fun search(condition: RecruitmentSearchCondition): List<RecruitmentSearchResult>
}
