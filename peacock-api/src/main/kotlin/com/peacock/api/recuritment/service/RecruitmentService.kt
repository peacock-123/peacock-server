package com.peacock.api.recuritment.service

import com.peacock.api.recuritment.service.dto.CreateRecruitmentCommand
import com.peacock.core.domain.recruitment.RecruitmentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RecruitmentService(
    private val recruitmentRepository: RecruitmentRepository,
) {
    @Transactional
    fun create(command: CreateRecruitmentCommand) {
        recruitmentRepository.save(command.toEntity())
    }
}
