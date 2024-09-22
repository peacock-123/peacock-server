package com.peacock.api.support

import org.springframework.transaction.support.TransactionTemplate

fun <T> TransactionTemplate.executeOrFail(block: () -> T): T = execute { block() } ?: error("TransactionTemplate returned null")
