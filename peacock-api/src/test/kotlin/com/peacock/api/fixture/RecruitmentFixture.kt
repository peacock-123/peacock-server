package com.peacock.api.fixture

import com.navercorp.fixturemonkey.ArbitraryBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.navercorp.fixturemonkey.kotlin.into
import com.navercorp.fixturemonkey.kotlin.setExp
import com.peacock.core.domain.recruitment.Recruitment
import com.peacock.core.domain.recruitment.RecruitmentPosition
import com.peacock.core.domain.recruitment.RecruitmentPositionGroup
import com.peacock.core.domain.recruitment.vo.RecruitmentId
import com.peacock.core.domain.recruitment.vo.RecruitmentInterval
import com.peacock.core.domain.recruitment.vo.RecruitmentMethod
import com.peacock.core.domain.recruitment.vo.RecruitmentPositionGroupId
import com.peacock.core.domain.recruitment.vo.RecruitmentType
import net.jqwik.api.Arbitraries

enum class RecruitmentFixture(
    private val block: ArbitraryBuilder<Recruitment>.() -> Unit,
) {
    PROJECT({
        setExp(Recruitment::type, RecruitmentType.PROJECT)
    }),
    STUDY({
        setExp(Recruitment::type, RecruitmentType.STURY)
    }),
    NETWORKING({
        setExp(Recruitment::type, RecruitmentType.NETWORKING)
    }),
    ;

    fun sample(additional: ArbitraryBuilder<Recruitment>.() -> Unit = {}): Recruitment =
        builder
            .copy()
            .apply(block)
            .apply(additional)
            .sample()

    companion object {
        private val STRING_ARBITRARY = Arbitraries.strings().alpha().ofMinLength(1)
        private val builder =
            DefaultFixture
                .giveMeBuilder<Recruitment>()
                .setExp(Recruitment::id, RecruitmentId(0))
                .setExp(Recruitment::title, STRING_ARBITRARY)
                .setExp(Recruitment::duration, STRING_ARBITRARY)
                .setExp(Recruitment::method into RecruitmentMethod::contact, STRING_ARBITRARY)
                .setExp(Recruitment::interval, RecruitmentInterval(RecruitmentInterval.Type.WEEKLY, 1))
                .setExp(
                    Recruitment::positionGroup,
                    DefaultFixture
                        .giveMeBuilder<RecruitmentPositionGroup>()
                        .setExp(RecruitmentPositionGroup::id, RecruitmentPositionGroupId(0))
                        .setExp(
                            RecruitmentPositionGroup::positions,
                            DefaultFixture.giveMeBuilder<RecruitmentPosition>().sampleList(1),
                        ).sampleList(1),
                )
    }
}
