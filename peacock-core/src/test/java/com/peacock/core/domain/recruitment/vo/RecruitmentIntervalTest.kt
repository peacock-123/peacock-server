package com.peacock.core.domain.recruitment.vo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RecruitmentIntervalTest {
    @Test
    @DisplayName("생성에 성공한다")
    fun success() {
        // given
        val type = RecruitmentInterval.Type.WEEKLY
        val frequency = 1

        // when
        val interval = RecruitmentInterval(type = type, frequency = frequency)

        // then
        assert(interval.type == type)
        assert(interval.frequency == frequency)
    }

    @Test
    @DisplayName("추후 협의 유형은 frequency가 null이어야 한다")
    fun fail() {
        // given
        val type = RecruitmentInterval.Type.TO_BE_DISCUSSED
        val frequency = 1

        // when
        val result =
            assertThrows<IllegalArgumentException> {
                RecruitmentInterval(type = type, frequency = frequency)
            }

        // then
        assert(result.message == "추후 협의는 빈 frequency를 가져야 합니다.")
    }

    @Test
    @DisplayName("추후 협의가 아닌 경우 frequency는 0보다 커야 한다")
    fun negative() {
        // given
        val type = RecruitmentInterval.Type.WEEKLY
        val frequency = 0

        // when
        val result =
            assertThrows<IllegalArgumentException> {
                RecruitmentInterval(type = type, frequency = frequency)
            }

        // then
        assert(result.message == "추후 협의가 아닌 경우 frequency는 0보다 커야 합니다.")
    }
}
