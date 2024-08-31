package com.peacock.api.recuritment.repository

import com.peacock.core.domain.recruitment.RecruitmentRepository

interface RecruitmentApiRepository :
    RecruitmentRepository,
    RecruitmentJooqRepository
