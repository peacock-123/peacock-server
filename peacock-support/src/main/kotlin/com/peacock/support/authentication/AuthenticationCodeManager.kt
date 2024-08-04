package com.peacock.support.authentication

import com.peacock.support.authentication.resolver.AuthenticationCodeResolver
import org.springframework.stereotype.Component

@Component
class AuthenticationCodeManager(
    private val codeResolvers: List<AuthenticationCodeResolver>,
)
