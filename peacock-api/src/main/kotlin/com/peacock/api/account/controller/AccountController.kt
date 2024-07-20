package com.peacock.api.account.controller

import com.peacock.core.domain.account.Account
import com.peacock.core.domain.account.AccountRepository
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountRepository: AccountRepository,
) {
    @GetMapping("/test")
    fun test(pageable: Pageable): List<Account> {
        val a = accountRepository.findAll(pageable).toList()

        return a
    }
}
