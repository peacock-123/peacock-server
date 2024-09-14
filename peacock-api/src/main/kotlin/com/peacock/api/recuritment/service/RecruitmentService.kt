package com.peacock.api.recuritment.service

import com.peacock.api.recuritment.repository.RecruitmentApiRepository
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.api.recuritment.service.dto.CreateRecruitmentCommand
import com.peacock.api.recuritment.service.dto.SearchRecruitmentDto
import com.peacock.core.domain.account.AccountRepository
import com.peacock.core.domain.position.PositionRepository
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RecruitmentService(
    private val recruitmentApiRepository: RecruitmentApiRepository,
    private val accountRepository: AccountRepository,
    private val positionRepository: PositionRepository,
) {
    @Transactional
    fun create(command: CreateRecruitmentCommand): RecruitmentId = recruitmentApiRepository.save(command.toEntity()).id

    fun search(condition: RecruitmentSearchCondition): Page<SearchRecruitmentDto> {
        val page = recruitmentApiRepository.search(condition)

        val accounts =
            accountRepository.findAllById(page.content.map { it.authorId.value }).associateBy { it.id }
        val positions =
            positionRepository
                .findAllById(
                    page.content.flatMap { result -> result.positions.map { it.value } },
                ).associateBy { it.id }

        return page.map { result ->
            SearchRecruitmentDto(
                id = result.id,
                createdAt = result.createdAt,
                type = result.type,
                title = result.title,
                endedAt = result.endedAt,
                positions = result.positions.map { positions.getValue(it).name },
                author = accounts.getValue(result.authorId).email.name,
                viewCount = 0,
                totalCapacity = result.totalCapacity.toLong(),
            )
        }
    }
}
