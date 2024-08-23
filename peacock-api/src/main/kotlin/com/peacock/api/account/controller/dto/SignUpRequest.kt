package com.peacock.api.account.controller.dto

import com.peacock.core.domain.account.vo.AuthProvider

data class SignUpRequest(
    val code: String,
    val provider: AuthProvider,
)
