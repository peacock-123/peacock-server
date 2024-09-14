package com.peacock.core.domain.vo

@JvmInline
value class NonNegativeLong(
    val value: Long,
) {
    init {
        require(value >= 0) { "값은 0 이상이어야 합니다" }
    }
}
