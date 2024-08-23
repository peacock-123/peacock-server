package com.peacock.core.domain.recruitment.vo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RecruitmentMethodTest {
    @Test
    @DisplayName("생성에 성공한다")
    fun create() {
        // given
        val type = RecruitmentMethod.Type.OPEN_CHAT
        val contact = "contact"

        // when
        val method = RecruitmentMethod(type = type, contact = contact)

        // then
        assert(method.type == RecruitmentMethod.Type.OPEN_CHAT)
        assert(method.contact == "contact")
    }

    @Test
    @DisplayName("연략처가 공백일 경우 실패한다")
    fun fail() {
        // given
        val type = RecruitmentMethod.Type.OPEN_CHAT
        val contact = "    "

        // when
        val result =
            assertThrows<IllegalArgumentException> {
                RecruitmentMethod(type = type, contact = contact)
            }

        // then
        assert(result.message == "contact must not be blank")
    }
}
