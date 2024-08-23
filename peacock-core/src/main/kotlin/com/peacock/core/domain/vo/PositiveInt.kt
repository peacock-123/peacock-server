package com.peacock.core.domain.vo

@JvmInline
value class PositiveInt(
    val value: Int,
) {
    init {
        require(value > 0) { "반드시 양수여야 합니다" }
    }
}
