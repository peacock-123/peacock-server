package com.peacock

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing

@OpenAPIDefinition(
    servers = [
        Server(url = "https://api.peacock.vm.cd80.run", description = "prod"),
        Server(url = "http://localhost:8080", description = "local"),
    ],
)
@SpringBootApplication
@EnableJdbcAuditing
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
