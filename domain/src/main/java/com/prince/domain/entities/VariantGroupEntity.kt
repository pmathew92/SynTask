package com.prince.domain.entities

data class VariantGroupEntity(
    val groupId: String,
    val name: String,
    val variationEntities: List<VariationEntity>,
    var selected: String = ""
)