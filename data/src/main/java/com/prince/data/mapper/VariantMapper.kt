package com.prince.data.mapper

import com.prince.data.model.ExclusionsNetwork
import com.prince.data.model.VariantGroupNetwork
import com.prince.data.model.VariantsNetwork
import com.prince.domain.entities.ExclusionsEntity
import com.prince.domain.entities.VariantGroupEntity
import com.prince.domain.entities.VariantsEntity
import javax.inject.Inject

class VariantMapper @Inject constructor(
    private val exclusionMapper: Mapper<ExclusionsNetwork, ExclusionsEntity>,
    var variantGroupEntityMapper: Mapper<VariantGroupNetwork, VariantGroupEntity>
) : Mapper<VariantsNetwork, VariantsEntity> {
    override fun from(t: VariantsNetwork): VariantsEntity {
        return VariantsEntity(
            t.excludeList.map { list -> list.map { exclusionMapper.from(it) } },
            t.variantGroupNetworks.map { variantGroupEntityMapper.from(it) }
        )
    }
}