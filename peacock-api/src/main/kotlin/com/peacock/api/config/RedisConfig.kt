package com.peacock.api.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration(proxyBeanMethods = false)
@EnableRedisHttpSession(
    redisNamespace = "\${spring.session.redis.namespace}",
    maxInactiveIntervalInSeconds = 86_400,
)
class RedisConfig {
    @Bean
    fun springSessionDefaultRedisSerializer(objectMapper: ObjectMapper): RedisSerializer<Any> =
        GenericJackson2JsonRedisSerializer(objectMapper)
}
