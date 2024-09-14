package com.peacock.core.domain.account.vo

enum class AuthProvider(
    val code: String,
) {
    GOOGLE(GOOGLE_CODE),
    KAKAO(KAKAO_CODE),
}

const val GOOGLE_CODE = "GOOGLE"
const val KAKAO_CODE = "KAKAO"
