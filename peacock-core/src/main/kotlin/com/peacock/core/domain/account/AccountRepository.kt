package com.peacock.core.domain.account

import com.peacock.core.domain.account.vo.AccountId
import com.peacock.core.domain.vo.Email
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, AccountId> {
    fun findByEmail(email: Email): Account?
}
