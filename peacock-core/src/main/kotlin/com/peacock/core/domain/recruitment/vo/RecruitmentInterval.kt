package com.peacock.core.domain.recruitment.vo

import org.springframework.data.relational.core.mapping.Column

class RecruitmentInterval(
    @Column("interval_type")
    val type: Type,
    @Column("interval_frequency")
    val frequency: Int?,
) {
    enum class Type(
        val displayName: String,
    ) {
        WEEKLY("주"),
        MONTHLY("월"),
        TO_BE_DISCUSSED("추후 협의"),
    }
}
