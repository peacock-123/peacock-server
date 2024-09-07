package com.peacock.api.recuritment.repository

import com.peacock.api.fixture.RecruitmentFixture
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.core.domain.recruitment.RecruitmentRepository
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import org.jooq.DSLContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class RecruitmentJooqRepositoryImplTest(
    private val recruitmentRepository: RecruitmentRepository,
    dslContext: DSLContext,
) {
    private val recruitmentJooqRepository = RecruitmentJooqRepositoryImpl(dslContext)

    @Nested
    inner class Search {
        @Test
        @DisplayName("주어진 유형에 해당하는 검색 결과를 반환한다.")
        fun search() {
            // given
            val recruitments =
                listOf(
                    RecruitmentFixture.STUDY.sample(),
                    RecruitmentFixture.PROJECT.sample(),
                    RecruitmentFixture.NETWORKING.sample(),
                )
            recruitmentRepository.saveAll(recruitments)

            val condition =
                RecruitmentSearchCondition(
                    type = listOf(RecruitmentType.STURY),
                    positionIds = emptyList(),
                    skillIds = emptyList(),
                    keyword = "",
                    pageable = PageRequest.of(0, 10),
                )

            // when
            val result = recruitmentJooqRepository.search(condition)

            // then
            assert(result.size == 1)
            assert(result.first().type == RecruitmentType.STURY)
        }
    }
}
