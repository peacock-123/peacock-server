package com.peacock.api.fixture

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeBuilder
import com.peacock.core.domain.vo.NonNegativeLong
import com.peacock.core.domain.vo.PositiveInt
import net.jqwik.api.Arbitraries

val DefaultFixture: FixtureMonkey =
    FixtureMonkey
        .builder()
        .plugin(KotlinPlugin())
        .register(PositiveInt::class.java) {
            it
                .giveMeBuilder<PositiveInt>()
                .set("value", Arbitraries.integers().greaterOrEqual(1).lessOrEqual(1_000_000))
        }.register(NonNegativeLong::class.java) {
            it
                .giveMeBuilder<NonNegativeLong>()
                .set("value", Arbitraries.integers().greaterOrEqual(0).lessOrEqual(1_000_000))
        }.build()
