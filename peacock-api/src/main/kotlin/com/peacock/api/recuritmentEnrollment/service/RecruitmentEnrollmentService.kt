package com.peacock.api.recuritmentEnrollment.service

import com.peacock.api.recuritmentEnrollment.repository.RecruitmentEnrollmentApiRepository
import com.peacock.api.recuritmentEnrollment.service.dto.EnrollRecruitmentCommand
import com.peacock.api.support.executeOrFail
import com.peacock.core.domain.position.PositionRepository
import com.peacock.core.domain.recruitment.RecruitmentRepository
import com.peacock.core.domain.recruitmentEnrollment.RecruitmentEnrollment
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentId
import com.peacock.core.exception.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class RecruitmentEnrollmentService(
    private val recruitmentRepository: RecruitmentRepository,
    private val recruitmentEnrollmentApiRepository: RecruitmentEnrollmentApiRepository,
    private val positionRepository: PositionRepository,
    private val transactionTemplate: TransactionTemplate,
) {
    fun enroll(command: EnrollRecruitmentCommand): RecruitmentEnrollmentId {
        if (!recruitmentRepository.existsById(command.recruitmentId.value)) {
            throw NotFoundException("모집글이 존재하지 않습니다: ${command.recruitmentId}")
        }

        if (!positionRepository.existsById(command.positionId.value)) {
            throw NotFoundException("포지션이 존재하지 않습니다: ${command.positionId}")
        }

        if (recruitmentEnrollmentApiRepository.existsByRecruitmentIdAndAccountId(
                recruitmentId = command.recruitmentId,
                accountId = command.accountId,
            )
        ) {
            throw IllegalArgumentException("이미 지원한 모집글입니다: ${command.recruitmentId} ${command.accountId}")
        }

        val recruitmentEnrollment =
            RecruitmentEnrollment(
                recruitmentId = command.recruitmentId,
                accountId = command.accountId,
                positionId = command.positionId,
                contact = command.contact,
                resumeLink = command.resumeLink,
            )

        return transactionTemplate.executeOrFail {
            recruitmentEnrollmentApiRepository.save(recruitmentEnrollment).id
        }
    }
}
