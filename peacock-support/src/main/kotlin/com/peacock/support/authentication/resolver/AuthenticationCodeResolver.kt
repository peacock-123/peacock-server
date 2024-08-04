package com.peacock.support.authentication.resolver

import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import com.peacock.support.authentication.AuthCode

interface AuthenticationCodeResolver {
    fun resolve(code: AuthCode): Email

    fun supports(provider: AuthProvider): Boolean
}
