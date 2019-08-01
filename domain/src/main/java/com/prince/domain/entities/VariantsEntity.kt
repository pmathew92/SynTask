package com.prince.domain.entities

data class VariantsEntity(
    val excludeList: List<List<ExclusionsEntity>>,
    val variantGroupEntities: List<VariantGroupEntity>
)