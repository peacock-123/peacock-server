package com.peacock.core.domain.account

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Account(
    @Id
    val id: AccountId,
    val email: Email,
    val authProvider: AuthProvider,
) {
    companion object {
        fun create(
            email: Email,
            provider: AuthProvider,
        ): Account =
            Account(
                id = AccountId(0),
                email = email,
                authProvider = provider,
            )
    }
}
