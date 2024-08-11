package com.peacock.core.domain.recruitment.vo

import org.springframework.data.relational.core.mapping.Column

data class RecruitmentMethod(
    @Column("method")
    val type: Type,
    val contact: String,
) {
    enum class Type(
        val displayName: String,
    ) {
        OPEN_CHAT("오픈채팅"),
        GOOGLE_FORM("구글폼"),
        EMAIL("메일"),
    }
}
