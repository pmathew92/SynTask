package com.prince.syntask.mapper

import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariationEntity
import com.prince.syntask.model.VariantGroup
import com.prince.syntask.model.Variation
import javax.inject.Inject

class VariantGroupEntityMapper @Inject constructor(private val mapper: Mapper<VariationEntity, Variation>) :
    Mapper<VariantGroupEntity, VariantGroup> {
    override fun from(t: VariantGroupEntity): VariantGroup {
        return VariantGroup(
            groupId = t.groupId,
            name = t.name,
            selected = t.selected,
            variations = t.variationEntities.map { mapper.from(it) }
        )
    }

}