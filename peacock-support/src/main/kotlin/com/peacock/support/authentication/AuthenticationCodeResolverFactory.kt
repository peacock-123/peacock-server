package com.peacock.support.authentication

import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.support.authentication.resolver.AuthenticationCodeResolver

interface AuthenticationCodeResolverFactory {
    fun getResolver(provider: AuthProvider): AuthenticationCodeResolver
}
