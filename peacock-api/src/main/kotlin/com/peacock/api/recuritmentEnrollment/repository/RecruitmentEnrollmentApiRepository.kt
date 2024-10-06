package com.peacock.api.recuritmentEnrollment.repository

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitmentEnrollment.RecruitmentEnrollmentRepository

interface RecruitmentEnrollmentApiRepository : RecruitmentEnrollmentRepository {
    fun existsByRecruitmentIdAndAccountId(
        recruitmentId: RecruitmentId,
        accountId: AccountId,
    ): Boolean
}
