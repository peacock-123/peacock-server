package com.peacock.support.authentication

import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import com.peacock.support.authentication.resolver.AuthenticationCodeResolver
import org.springframework.stereotype.Component

@Component
class AuthenticationCodeManager(
    private val codeResolvers: List<AuthenticationCodeResolver>,
) {
    fun resolve(
        provider: AuthProvider,
        code: AuthCode,
    ): Email =
        codeResolvers.find { it.supports(provider) }?.resolve(code)
            ?: throw IllegalArgumentException("Unsupported provider: $provider")
}
