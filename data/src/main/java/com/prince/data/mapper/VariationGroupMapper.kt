package com.prince.data.mapper

import com.prince.data.model.VariantGroupNetwork
import com.prince.data.model.VariationNetwork
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariationEntity
import javax.inject.Inject

class VariationGroupMapper @Inject constructor(private val mapper: Mapper<VariationNetwork, VariationEntity>) :
    Mapper<VariantGroupNetwork, VariantGroupEntity> {
    override fun from(t: VariantGroupNetwork): VariantGroupEntity {
        return VariantGroupEntity(
            groupId = t.groupId,
            name = t.name,
            selected = t.selected,
            variationEntities = t.variationNetworks.map { mapper.from(it) }
        )
    }
}