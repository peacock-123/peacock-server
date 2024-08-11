package com.peacock.core.domain.account

import com.peacock.core.domain.vo.Email
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long> {
    fun findByEmail(email: Email): Account?
}
