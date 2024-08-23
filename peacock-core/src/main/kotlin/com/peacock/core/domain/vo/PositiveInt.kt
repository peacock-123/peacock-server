package com.peacock.core.domain.vo

@JvmInline
value class PositiveInt(
    val value: Int,
) {
    init {
        require(value > 0) { "PositiveInt must be greater than 0" }
    }
}
