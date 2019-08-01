package com.prince.syntask.mapper

import com.prince.domain.entities.VariationEntity
import com.prince.syntask.model.Variation
import javax.inject.Inject

class VariationEntityMapper @Inject constructor() : Mapper<VariationEntity, Variation> {
    override fun from(t: VariationEntity): Variation {
        return Variation(
            t.default,
            t.id,
            t.inStock,
            t.name,
            t.price
        )
    }

}