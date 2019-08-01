package com.prince.data.mapper

import com.prince.data.model.VariationNetwork
import com.prince.domain.entities.VariationEntity
import javax.inject.Inject

class VariationMapper @Inject constructor() : Mapper<VariationNetwork, VariationEntity> {
    override fun from(t: VariationNetwork): VariationEntity {
        return VariationEntity(
            default = t.default,
            id = t.id,
            inStock = t.inStock,
            name = t.name,
            price = t.price
        )
    }
}