package com.prince.syntask.mapper

import com.prince.domain.entities.ExclusionsEntity
import com.prince.syntask.model.Exclusions
import javax.inject.Inject

class ExclusionEntityMapper @Inject constructor() : Mapper<ExclusionsEntity, Exclusions> {
    override fun from(t: ExclusionsEntity): Exclusions {
        return Exclusions(
            t.groupId,
            t.variationId
        )
    }

}