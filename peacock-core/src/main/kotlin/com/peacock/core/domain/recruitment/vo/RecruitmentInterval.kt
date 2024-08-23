package com.peacock.core.domain.recruitment.vo

import org.springframework.data.relational.core.mapping.Column

data class RecruitmentInterval(
    @Column("interval_type")
    val type: Type,
    @Column("interval_frequency")
    val frequency: Int?,
) {
    init {
        require(type != Type.TO_BE_DISCUSSED || frequency == null) {
            "추후 협의는 빈 frequency를 가져야 합니다."
        }

        require(type == Type.TO_BE_DISCUSSED || frequency != null && frequency > 0) {
            "추후 협의가 아닌 경우 frequency는 0보다 커야 합니다."
        }
    }

    enum class Type(
        val displayName: String,
    ) {
        WEEKLY("주"),
        MONTHLY("월"),
        TO_BE_DISCUSSED("추후 협의"),
    }
}
