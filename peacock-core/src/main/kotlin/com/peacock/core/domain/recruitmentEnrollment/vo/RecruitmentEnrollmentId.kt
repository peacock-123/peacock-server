package com.peacock.core.domain.recruitmentEnrollment.vo

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import java.io.Serializable

data class RecruitmentEnrollmentId(
    val recruitmentId: RecruitmentId,
    val accountId: AccountId,
) : Serializable
