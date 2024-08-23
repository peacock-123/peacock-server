package com.peacock.api.session

import com.peacock.core.domain.account.vo.AccountId

data class SessionId(
    val value: Long,
) {
    fun toAccountId(): AccountId = AccountId(value)
}
