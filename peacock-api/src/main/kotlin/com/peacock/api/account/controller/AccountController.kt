package com.peacock.api.account.controller

import com.peacock.api.account.controller.dto.SignUpRequest
import com.peacock.api.account.service.AccountService
import com.peacock.api.session.SessionId
import com.peacock.support.authentication.AuthCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(
    private val accountService: AccountService,
) {
    private val httpSessionSecurityContextRepository = HttpSessionSecurityContextRepository()

    @PostMapping("/sign-in")
    fun signIn(
        request: HttpServletRequest,
        response: HttpServletResponse,
        @RequestBody signUpRequest: SignUpRequest,
    ) {
        val accountId =
            accountService.signIn(
                code = AuthCode(signUpRequest.code),
                provider = signUpRequest.provider,
            )

        val token =
            UsernamePasswordAuthenticationToken.authenticated(
                SessionId(accountId.value),
                "",
                emptySet(),
            )
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = token
        SecurityContextHolder.setContext(context)
        httpSessionSecurityContextRepository.saveContext(context, request, response)
    }
}
