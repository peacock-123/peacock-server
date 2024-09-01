package com.peacock.api.recuritment.repository

import com.peacock.api.recuritment.repository.dto.RecruitmentSearchCondition
import com.peacock.api.recuritment.repository.dto.RecruitmentSearchResult
import com.peacock.core.extension.asNonNullField
import org.jooq.DSLContext
import org.jooq.Records
import org.jooq.generated.tables.references.RECRUITMENT
import org.jooq.generated.tables.references.RECRUITMENT_POSITION
import org.jooq.generated.tables.references.RECRUITMENT_POSITION_GROUP
import org.jooq.generated.tables.references.RECRUITMENT_SKILL
import org.jooq.impl.DSL.arrayAgg

class RecruitmentJooqRepositoryImpl(
    private val dsl: DSLContext,
) : RecruitmentJooqRepository {
    override fun search(condition: RecruitmentSearchCondition): List<RecruitmentSearchResult> =
        dsl
            .select(
                RECRUITMENT.ID.asNonNullField(),
                RECRUITMENT.CREATED_AT.asNonNullField(),
                RECRUITMENT.TYPE.asNonNullField(),
                RECRUITMENT.TITLE.asNonNullField(),
                RECRUITMENT.ENDED_AT,
                arrayAgg(RECRUITMENT_POSITION.POSITION_ID.asNonNullField()).orderBy(RECRUITMENT_POSITION.SEQUENCE),
            ).from(RECRUITMENT)
            .innerJoin(RECRUITMENT_POSITION_GROUP)
            .on(RECRUITMENT.ID.eq(RECRUITMENT_POSITION_GROUP.RECRUITMENT_ID))
            .innerJoin(RECRUITMENT_POSITION)
            .on(RECRUITMENT_POSITION_GROUP.ID.eq(RECRUITMENT_POSITION.RECRUITMENT_POSITION_GROUP_ID))
            .apply {
                if (condition.type.isNotEmpty()) {
                    where(RECRUITMENT.TYPE.`in`(condition.type.map { it.name }))
                }

                if (condition.positionIds.isNotEmpty()) {
                    where(RECRUITMENT_POSITION.POSITION_ID.`in`(condition.positionIds.map { it.value }))
                }

                if (condition.skillIds.isNotEmpty()) {
                    innerJoin(RECRUITMENT_SKILL)
                        .on(RECRUITMENT_POSITION_GROUP.ID.eq(RECRUITMENT_SKILL.RECRUITMENT_POSITION_GROUP_ID))
                        .where(RECRUITMENT_SKILL.SKILL_ID.`in`(condition.skillIds.map { it.value }))
                }

                if (condition.keyword.isNotBlank()) {
                    where(RECRUITMENT.TITLE.containsIgnoreCase(condition.keyword))
                }
            }.groupBy(RECRUITMENT.ID)
            .orderBy(RECRUITMENT.ID)
            .offset(condition.pageable.offset)
            .limit(condition.pageable.pageSize)
            .fetch(Records.mapping(::RecruitmentSearchResult))
}
