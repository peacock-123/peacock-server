package com.peacock.core.domain.vo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NonNegativeLongTest {
    @Test
    @DisplayName("생성")
    fun create() {
        // given
        val value = 1L

        // when
        val result = NonNegativeLong(value)

        // then
        assert(result.value == value)
    }

    @Test
    @DisplayName("음수를 입력하면 예외가 발생한다")
    fun minus() {
        // given
        val value = -1L

        // when
        val result =
            assertThrows<IllegalArgumentException> { NonNegativeLong(value) }

        // then
        assert(result.message == "값은 0 이상이어야 합니다")
    }

    @Test
    @DisplayName("0을 입력하면 예외가 발생하지 않는다")
    fun zero() {
        // given
        val value = 0L

        // when
        val result = NonNegativeLong(value)

        // then
        assert(result.value == value)
    }
}
