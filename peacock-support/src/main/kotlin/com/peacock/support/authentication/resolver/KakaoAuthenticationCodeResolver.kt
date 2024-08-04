package com.peacock.support.authentication.resolver

import com.peacock.core.domain.vo.Email
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
    override fun resolve(code: String): Email {
        val accessToken =
            kakaoHttpClient
                .getToken(
                    KakaoHttpClient.KakaoAccessTokenRequest(
                        grantType = REQUIRED_GRANT_TYPE,
                        clientId = clientId,
                        redirectUri = redirectUri,
                        clientSecret = clientSecret,
                        code = code,
                    ),
                ).accessToken

        val profile = getProfile(accessToken)

        return Email(profile.kakaoAccount.email)
    }

    private fun getProfile(accessToken: String): KakaoHttpClient.KakaoProfileResponse = kakaoHttpClient.getProfile("Bearer $accessToken")

    companion object {
        private const val REQUIRED_GRANT_TYPE = "authorization_code"
    }
}
