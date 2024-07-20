package com.peacock.core.domain.account

import com.peacock.core.domain.account.vo.AccountId
import org.springframework.data.repository.PagingAndSortingRepository

interface AccountRepository : PagingAndSortingRepository<Account, AccountId>
