package com.peacock.support.authentication

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient

@Configuration(proxyBeanMethods = false)
class AuthenticationHttpClientConfig {
    @Bean
    fun kakoaHttpClient(builder: RestClient.Builder): KakaoHttpClient =
        HttpServiceProxyFactory
            .builderFor(RestClientAdapter.create(builder.build()))
            .build()
            .createClient()
}
