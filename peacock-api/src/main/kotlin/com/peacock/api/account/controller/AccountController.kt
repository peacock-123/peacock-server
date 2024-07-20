package com.peacock.api.account.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController {
    private val httpSessionSecurityContextRepository = HttpSessionSecurityContextRepository()

    @PostMapping("/sign-up")
    fun singUp(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?,
    ) {
        val token =
            UsernamePasswordAuthenticationToken.authenticated(
                "user",
                "pass",
                listOf(),
            )
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = token
        SecurityContextHolder.setContext(context)
        httpSessionSecurityContextRepository.saveContext(context, request, response)
    }
}
