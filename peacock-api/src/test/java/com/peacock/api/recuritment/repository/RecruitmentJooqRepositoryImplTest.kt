package com.peacock.api.recuritment.repository

import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
import org.springframework.boot.test.autoconfigure.jooq.JooqTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.TestConstructor

@JooqTest
@ImportAutoConfiguration(FlywayAutoConfiguration::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RecruitmentJooqRepositoryImplTest(
    dslContext: DSLContext,
) {
    private val recruitmentJooqRepository = RecruitmentJooqRepositoryImpl(dslContext)

    @BeforeEach
    fun clearDatabase(
        @Autowired flyway: Flyway,
    ) {
        flyway.migrate()
    }

    @Nested
    inner class Search {
        @Test
        @DisplayName("검색 조건이 주어지면 검색 결과를 반환한다.")
        fun search() {
            // given
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
            assert(result.isNotEmpty())
        }
    }
}
