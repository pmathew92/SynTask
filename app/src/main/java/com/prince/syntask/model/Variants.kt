package com.prince.syntask.model

import com.squareup.moshi.Json

data class Variants(
    @Json(name = "exclude_list")
    val excludeList: List<List<Exclusions>>,
    @Json(name = "variant_groups")
    val variantGroups: List<VariantGroup>
)