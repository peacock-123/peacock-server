package com.peacock.api.config

import com.peacock.core.exception.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {
    private val logger = LoggerFactory.getLogger(ControllerAdvice::class.java)

    data class ErrorResponse(
        val message: String,
    )

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        e: NotFoundException,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        logger.error("Request: ${request.method} ${request.requestURI}", e)

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorResponse(e.message ?: "Not Found"),
        )
    }

    @ExceptionHandler
    fun handleException(
        e: Throwable,
        request: HttpServletRequest,
    ): ResponseEntity<ErrorResponse> {
        logger.error("Request: ${request.method} ${request.requestURI}", e)

        return ResponseEntity.internalServerError().body(
            ErrorResponse("Internal Server Error"),
        )
    }
}
