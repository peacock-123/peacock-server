package com.peacock.support.authentication.resolver

import com.peacock.core.domain.vo.Email

interface AuthenticationCodeResolver {
    fun resolve(code: String): Email
}
