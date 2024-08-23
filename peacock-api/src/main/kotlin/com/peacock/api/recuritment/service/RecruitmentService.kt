package com.peacock.api.recuritment.service

import com.peacock.api.recuritment.service.dto.CreateRecruitmentCommand
import com.peacock.core.domain.recruitment.RecruitmentRepository
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RecruitmentService(
    private val recruitmentRepository: RecruitmentRepository,
) {
    @Transactional
    fun create(command: CreateRecruitmentCommand): RecruitmentId = recruitmentRepository.save(command.toEntity()).id
}
