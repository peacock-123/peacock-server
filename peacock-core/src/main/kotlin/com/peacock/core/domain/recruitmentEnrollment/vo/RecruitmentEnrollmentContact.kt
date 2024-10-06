package com.peacock.core.domain.recruitmentEnrollment.vo

import org.springframework.data.relational.core.mapping.Column

data class RecruitmentEnrollmentContact(
    @Column("contact_method")
    val method: String,
    @Column("contact_content")
    val content: String,
)
