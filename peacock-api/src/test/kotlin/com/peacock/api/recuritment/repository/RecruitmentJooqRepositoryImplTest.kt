package com.peacock.api.recuritment.repository

import com.navercorp.fixturemonkey.kotlin.get
import com.navercorp.fixturemonkey.kotlin.setExp
import com.peacock.api.fixture.RecruitmentFixture
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.core.domain.position.vo.PositionId
import com.peacock.core.domain.recruitment.Recruitment
import com.peacock.core.domain.recruitment.RecruitmentPosition
import com.peacock.core.domain.recruitment.RecruitmentPositionGroup
import com.peacock.core.domain.recruitment.RecruitmentRepository
import com.peacock.core.domain.recruitment.RecruitmentSkill
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import com.peacock.core.domain.skill.vo.SkillId
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
            assert(result.numberOfElements == 1)
            assert(result.first().type == RecruitmentType.STURY)
        }

        @Test
        @DisplayName("주어진 포지션 아이디에 해당하는 검색 결과를 반환한다.")
        fun positionId() {
            // given
            val study =
                RecruitmentFixture.STUDY.sample {
                    setExp(
                        Recruitment::positionGroup[0] into RecruitmentPositionGroup::positions[0] into RecruitmentPosition::positionId,
                        PositionId(100),
                    )
                }
            val project =
                RecruitmentFixture.PROJECT.sample {
                    setExp(
                        Recruitment::positionGroup[0] into RecruitmentPositionGroup::positions[0] into RecruitmentPosition::positionId,
                        PositionId(200),
                    )
                }
            recruitmentRepository.saveAll(listOf(study, project))

            val condition =
                RecruitmentSearchCondition(
                    type = emptyList(),
                    positionIds = listOf(PositionId(100)),
                    skillIds = emptyList(),
                    keyword = "",
                    pageable = PageRequest.of(0, 10),
                )

            // when
            val result = recruitmentJooqRepository.search(condition)

            // then
            assert(result.numberOfElements == 1)
            assert(result.first().type == RecruitmentType.STURY)
        }

        @Test
        @DisplayName("주어진 스킬 아이디에 해당하는 검색 결과를 반환한다.")
        fun skillId() {
            // given
            val study =
                RecruitmentFixture.STUDY.sample {
                    setExp(
                        Recruitment::positionGroup[0] into RecruitmentPositionGroup::skills[0] into RecruitmentSkill::skillId,
                        SkillId(100),
                    )
                }
            val project =
                RecruitmentFixture.PROJECT.sample {
                    setExp(
                        Recruitment::positionGroup[0] into RecruitmentPositionGroup::skills[0] into RecruitmentSkill::skillId,
                        SkillId(200),
                    )
                }
            recruitmentRepository.saveAll(listOf(study, project))

            val condition =
                RecruitmentSearchCondition(
                    type = emptyList(),
                    positionIds = emptyList(),
                    skillIds = listOf(SkillId(200)),
                    keyword = "",
                    pageable = PageRequest.of(0, 10),
                )

            // when
            val result = recruitmentJooqRepository.search(condition)

            // then
            assert(result.numberOfElements == 1)
            assert(result.first().type == RecruitmentType.PROJECT)
        }

        @Test
        @DisplayName("주어진 키워드에 해당하는 검색 결과를 반환한다.")
        fun keyword() {
            // given
            val study =
                RecruitmentFixture.STUDY.sample {
                    setExp(Recruitment::title, "스터디")
                }
            val project =
                RecruitmentFixture.PROJECT.sample {
                    setExp(Recruitment::title, "프로젝트")
                }
            recruitmentRepository.saveAll(listOf(study, project))

            val condition =
                RecruitmentSearchCondition(
                    type = emptyList(),
                    positionIds = emptyList(),
                    skillIds = emptyList(),
                    keyword = "스터디",
                    pageable = PageRequest.of(0, 10),
                )

            // when
            val result = recruitmentJooqRepository.search(condition)

            // then
            assert(result.numberOfElements == 1)
            assert(result.first().title == "스터디")
        }

        @Test
        @DisplayName("페이징 처리된 검색 결과를 반환한다.")
        fun paging() {
            // given
            val recruitments =
                (0 until 20).map {
                    RecruitmentFixture.STUDY.sample {
                        setExp(Recruitment::title, "스터디$it")
                    }
                }
            recruitmentRepository.saveAll(recruitments)

            val condition =
                RecruitmentSearchCondition(
                    type = emptyList(),
                    positionIds = emptyList(),
                    skillIds = emptyList(),
                    keyword = "",
                    pageable = PageRequest.of(1, 10),
                )

            // when
            val result = recruitmentJooqRepository.search(condition)

            // then
            assert(result.numberOfElements == 10)
            assert(result.first().title == "스터디10")
        }
    }
}
