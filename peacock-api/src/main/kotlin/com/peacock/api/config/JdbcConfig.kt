package com.peacock.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing

@Configuration(proxyBeanMethods = false)
@EnableJdbcAuditing
class JdbcConfig {
//    @Bean
//    fun auditorProvider(): AuditorAware<Any> = AuditorAware<Any> { Optional.empty() }
//
//    @Bean
//    fun beforeSaveCallback() =
//        BeforeSaveCallback { aggregate, _ ->
//            aggregate::class.memberProperties.forEach { property ->
//                when {
//                    property.findAnnotation<CreatedDate>() != null -> {
//                        val createdAt = property.getter.call(aggregate)
//                        if (createdAt == null) {
//                            property.setter.call(aggregate, LocalDateTime.now())
//                        }
//                    }
//                    property.findAnnotation<LastModifiedDate>() != null -> {
//                        property.setter.call(aggregate, LocalDateTime.now())
//                    }
//                }
//            }
//            aggregate
//        }
}
