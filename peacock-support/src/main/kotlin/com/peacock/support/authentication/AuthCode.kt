package com.peacock.support.authentication

@JvmInline
value class AuthCode(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "AuthCode must not be blank" }
    }
}
