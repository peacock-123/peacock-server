package com.peacock.support.authentication.resolver

import com.peacock.core.domain.vo.Email
import com.peacock.support.authentication.AuthCode

interface AuthenticationCodeResolver {
    fun resolve(
        code: AuthCode,
        redirectUri: String?,
    ): Email
}
