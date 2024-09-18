package com.peacock.api.account.service

import com.peacock.core.domain.account.Account
import com.peacock.core.domain.account.AccountRepository
import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import com.peacock.support.authentication.AuthCode
import com.peacock.support.authentication.AuthenticationCodeResolverFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    private val authenticationCodeResolverFactory: AuthenticationCodeResolverFactory,
    private val accountRepository: AccountRepository,
) {
    @Transactional
    fun signIn(
        code: AuthCode,
        provider: AuthProvider,
        redirectUri: String?,
    ): AccountId {
        val email = authenticationCodeResolverFactory.getResolver(provider).resolve(code, redirectUri)
        val account = accountRepository.findByEmail(email) ?: signUp(email, provider)

        return account.id
    }

    private fun signUp(
        email: Email,
        provider: AuthProvider,
    ): Account {
        val account = Account.create(email, provider)
        accountRepository.save(account)

        return account
    }
}
