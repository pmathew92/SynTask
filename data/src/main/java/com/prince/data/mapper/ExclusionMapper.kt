package com.prince.data.mapper

import com.prince.data.model.ExclusionsNetwork
import com.prince.domain.entities.ExclusionsEntity
import javax.inject.Inject

class ExclusionMapper @Inject constructor() : Mapper<ExclusionsNetwork, ExclusionsEntity> {
    override fun from(t: ExclusionsNetwork): ExclusionsEntity {
        return ExclusionsEntity(
            t.groupId,
            t.variationId
        )
    }
}