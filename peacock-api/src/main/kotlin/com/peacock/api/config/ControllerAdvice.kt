package com.peacock.api.config

import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    private val logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(
        e: Throwable,
        request: HttpServletRequest,
    ): ResponseEntity<String> {
        logger.error("Request: ${request.method} ${request.requestURI}", e)

        return ResponseEntity.internalServerError().body("Internal Server Error")
    }
}
