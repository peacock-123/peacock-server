package com.peacock.core.domain.account

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class Account(
    @Id
    val id: AccountId,
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @LastModifiedDate
    val updatedAt: LocalDateTime = LocalDateTime.now(),
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
