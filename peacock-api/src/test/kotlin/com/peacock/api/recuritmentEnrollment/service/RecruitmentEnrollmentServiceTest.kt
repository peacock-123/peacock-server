package com.peacock.api.recuritmentEnrollment.service

import com.peacock.api.fixture.RecruitmentFixture
import com.peacock.api.recuritmentEnrollment.repository.RecruitmentEnrollmentApiRepository
import com.peacock.api.recuritmentEnrollment.service.dto.EnrollRecruitmentCommand
import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.position.Position
import com.peacock.core.domain.position.PositionRepository
import com.peacock.core.domain.recruitment.RecruitmentRepository
import com.peacock.core.domain.recruitmentEnrollment.vo.RecruitmentEnrollmentContact
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class RecruitmentEnrollmentServiceTest(
    private val recruitmentEnrollmentService: RecruitmentEnrollmentService,
    private val recruitmentRepository: RecruitmentRepository,
    private val recruitmentEnrollmentApiRepository: RecruitmentEnrollmentApiRepository,
) {
    @Autowired
    private lateinit var positionRepository: PositionRepository

    @Nested
    inner class Enroll {
        @Test
        @DisplayName("지원자가 채용 공고에 지원한다.")
        fun success() {
            // given
            val recruitment = RecruitmentFixture.PROJECT.sample().let { recruitmentRepository.save(it) }
            val position = positionRepository.save(Position(name = "백엔드"))
            val accountId = AccountId(123)

            val command =
                EnrollRecruitmentCommand(
                    recruitmentId = recruitment.id,
                    positionId = position.id,
                    accountId = accountId,
                    contact =
                        RecruitmentEnrollmentContact(
                            method = RecruitmentEnrollmentContact.Method.EMAIL,
                            content = "le**j20*2@naver.com",
                        ),
                    resumeLink = "https://github.com/lifeisegg123",
                )

            // when
            recruitmentEnrollmentService.enroll(command)

            // then
            val expect =
                recruitmentEnrollmentApiRepository.existsByRecruitmentIdAndAccountId(
                    recruitmentId = recruitment.id,
                    accountId = accountId,
                )
            assert(expect)
        }
    }
}
