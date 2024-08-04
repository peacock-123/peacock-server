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
    val provider: AuthProvider,
)
