package com.peacock.core.domain.vo

@JvmInline
value class Email(
    val value: String,
) {
    val name: String
        get() = value.substringBefore('@')
}
