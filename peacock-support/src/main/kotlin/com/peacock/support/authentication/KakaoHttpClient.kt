package com.peacock.support.authentication

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange("https://kauth.kakao.com")
interface KakaoHttpClient {
    @PostExchange("/oauth/token")
    fun getToken(request: KakaoAccessTokenRequest): KakaoAccessTokenResponse

    data class KakaoAccessTokenRequest(
        @JsonProperty("grant_type")
        val grantType: String,
        @JsonProperty("client_id")
        val clientId: String,
        @JsonProperty("redirect_uri")
        val redirectUri: String,
        val code: String,
        @JsonProperty("client_secret")
        val clientSecret: String,
    )

    data class KakaoAccessTokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
    )

    @PostExchange("/v2/user/me")
    fun getProfile(
        @RequestHeader("Authorization") accessToken: String,
    ): KakaoProfileResponse

    data class KakaoProfileResponse(
        val id: Long,
        @JsonProperty("kakao_account")
        val kakaoAccount: KakaoAccount,
    ) {
        data class KakaoAccount(
            val email: String,
        )
    }
}
