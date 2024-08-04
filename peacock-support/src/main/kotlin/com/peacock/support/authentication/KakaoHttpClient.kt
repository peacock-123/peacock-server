package com.peacock.support.authentication

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.PostExchange

interface KakaoHttpClient {
    @PostExchange("https://kauth.kakao.com/oauth/token", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    fun getToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("code") code: String,
        @RequestParam("client_secret") clientSecret: String,
    ): KakaoAccessTokenResponse

    data class KakaoAccessTokenResponse(
        @JsonProperty("access_token")
        val accessToken: String,
    )

    @PostExchange("https://kapi.kakao.com/v2/user/me", contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
