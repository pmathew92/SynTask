package com.prince.syntask.mapper

import com.prince.domain.entities.ExclusionsEntity
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariantsEntity
import com.prince.syntask.model.Exclusions
import com.prince.syntask.model.VariantGroup
import com.prince.syntask.model.Variants
import javax.inject.Inject

class VariantEntityMapper @Inject constructor(
    private val exclusionMapper: Mapper<ExclusionsEntity, Exclusions>,
    var variantGroupEntityMapper: Mapper<VariantGroupEntity, VariantGroup>
) :
    Mapper<VariantsEntity, Variants> {
    override fun from(t: VariantsEntity): Variants {
        return Variants(
            t.excludeList.map { list -> list.map { exclusionMapper.from(it) } },
            t.variantGroupEntities.map { variantGroupEntityMapper.from(it) }
        )
    }

}