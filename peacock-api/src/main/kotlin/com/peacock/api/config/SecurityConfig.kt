package com.peacock.api.config

import com.peacock.api.session.SessionId
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.savedrequest.NullRequestCache
import org.springframework.web.filter.OncePerRequestFilter

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
class SecurityConfig {
    @Bean
    fun filterChain(
        http: HttpSecurity,
        environment: Environment,
    ): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { disable() }
            formLogin { disable() }
            logout { disable() }
            requestCache { requestCache = NullRequestCache() }

            authorizeHttpRequests {
                authorize("/swagger-ui/**", permitAll)
                authorize("/v3/api-docs/**", permitAll)
                authorize("/api/v1/accounts/sign-in", permitAll)
                authorize(anyRequest, authenticated)
            }

            if (environment.matchesProfiles("local")) {
                addFilterBefore<UsernamePasswordAuthenticationFilter>(SessionBypassFilter())
            }
        }

        return http.build()
    }

    class SessionBypassFilter : OncePerRequestFilter() {
        override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain,
        ) {
            val sessionId: String? = request.getHeader("X-Session-Id")

            sessionId?.toLongOrNull()?.let {
                val token =
                    UsernamePasswordAuthenticationToken.authenticated(
                        SessionId(it),
                        "",
                        emptySet(),
                    )
                val context = SecurityContextHolder.createEmptyContext()
                context.authentication = token
                SecurityContextHolder.setContext(context)
            }

            filterChain.doFilter(request, response)
        }
    }
}
