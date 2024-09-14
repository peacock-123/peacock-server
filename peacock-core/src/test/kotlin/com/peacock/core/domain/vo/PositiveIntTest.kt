package com.peacock.core.domain.vo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PositiveIntTest {
    @Test
    @DisplayName("생성")
    fun create() {
        // given
        val value = 1

        // when
        val result = PositiveInt(value)

        // then
        assert(result.value == value)
    }

    @Test
    @DisplayName("음수를 입력하면 예외가 발생한다")
    fun minus() {
        // given
        val value = -1

        // when
        val result =
            assertThrows<IllegalArgumentException> { PositiveInt(value) }

        // then
        assert(result.message == "반드시 양수여야 합니다")
    }

    @Test
    @DisplayName("0을 입력하면 예외가 발생한다")
    fun zero() {
        // given
        val value = 0

        // when
        val result = assertThrows<IllegalArgumentException> { PositiveInt(value) }

        // then
        assert(result.message == "반드시 양수여야 합니다")
    }
}
