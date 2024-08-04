package com.peacock.support.authentication.resolver

import com.peacock.core.domain.account.vo.AuthProvider
import com.peacock.core.domain.vo.Email
import com.peacock.support.authentication.AuthCode
import com.peacock.support.authentication.KakaoHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class KakaoAuthenticationCodeResolver(
    @Value("\${kakao.clientId}") private val clientId: String,
    @Value("\${kakao.clientSecret}") private val clientSecret: String,
    @Value("\${kakao.redirectUri}") private val redirectUri: String,
    private val kakaoHttpClient: KakaoHttpClient,
) : AuthenticationCodeResolver {
    override fun resolve(code: AuthCode): Email {
        val accessToken =
            kakaoHttpClient
                .getToken(
                    grantType = REQUIRED_GRANT_TYPE,
                    clientId = clientId,
                    redirectUri = redirectUri,
                    clientSecret = clientSecret,
                    code = code.value,
                ).accessToken

        val profile = getProfile(accessToken)

        return Email(profile.kakaoAccount.email)
    }

    override fun supports(provider: AuthProvider): Boolean = provider == AuthProvider.KAKAO

    private fun getProfile(accessToken: String) = kakaoHttpClient.getProfile("Bearer $accessToken")

    companion object {
        private const val REQUIRED_GRANT_TYPE = "authorization_code"
    }
}
