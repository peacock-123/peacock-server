package com.peacock.support.authentication.resolver

import com.peacock.support.authentication.AuthCode
import com.peacock.support.authentication.KakaoHttpClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient

@Disabled
class KakaoAuthenticationCodeResolverTest {
    private val kakaoHttpClient: KakaoHttpClient =
        HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(RestClient.builder().build()))
            .build()
            .createClient()

    private val resolver =
        KakaoAuthenticationCodeResolver(
            clientId = "",
            clientSecret = "",
            redirectUri = "",
            kakaoHttpClient = kakaoHttpClient,
        )

    @Test
    @DisplayName("프로필 조회할 수 있다")
    fun profile() {
        // given
        // https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
        val code = AuthCode("code")

        // when
        val expected = resolver.resolve(code, redirectUri)

        // then
        println(expected)
    }
}
