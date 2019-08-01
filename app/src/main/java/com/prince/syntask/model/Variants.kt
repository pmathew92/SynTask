package com.prince.syntask.model

data class Variants(
    val excludeList: List<List<Exclusions>>,
    val variantGroups: List<VariantGroup>
)