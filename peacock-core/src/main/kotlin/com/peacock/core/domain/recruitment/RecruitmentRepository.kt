package com.peacock.core.domain.recruitment

import com.peacock.core.domain.recruitment.vo.RecruitmentId
import org.springframework.data.repository.ListCrudRepository

interface RecruitmentRepository : ListCrudRepository<Recruitment, RecruitmentId>
