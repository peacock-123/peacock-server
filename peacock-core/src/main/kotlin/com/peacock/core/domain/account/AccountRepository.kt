package com.peacock.core.domain.account

import com.peacock.core.domain.vo.Email
import org.springframework.data.repository.ListCrudRepository

interface AccountRepository : ListCrudRepository<Account, Long> {
    fun findByEmail(email: Email): Account?
}
