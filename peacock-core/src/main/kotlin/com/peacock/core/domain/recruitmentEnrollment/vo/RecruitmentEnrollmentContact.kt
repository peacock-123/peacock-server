package com.peacock.core.domain.recruitmentEnrollment.vo

import org.springframework.data.relational.core.mapping.Column

data class RecruitmentEnrollmentContact(
    @Column("contact_method")
    val method: Method,
    @Column("contact_content")
    val content: String,
) {
    enum class Method {
        EMAIL,
        PHONE,
    }
}
