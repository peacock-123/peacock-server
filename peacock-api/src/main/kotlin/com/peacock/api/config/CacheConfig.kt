package com.peacock.api.config

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.jcache.JCacheCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.cache.Caching
import javax.cache.configuration.MutableConfiguration
import javax.cache.expiry.CreatedExpiryPolicy
import javax.cache.expiry.Duration
import javax.cache.spi.CachingProvider

@Configuration
@EnableCaching
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager {
        val provider: CachingProvider = Caching.getCachingProvider()
        val cacheManager = provider.cacheManager

        val configuration =
            MutableConfiguration<String, Any>()
                .setTypes(String::class.java, Any::class.java)
                .setStoreByValue(false)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.TEN_MINUTES))

        cacheManager.createCache("static", configuration)

        return JCacheCacheManager(cacheManager)
    }
}
