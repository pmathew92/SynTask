package com.prince.data.model

import com.squareup.moshi.Json

data class VariantsNetwork(
    @Json(name = "exclude_list")
    val excludeList: List<List<ExclusionsNetwork>>,
    @Json(name = "variant_groups")
    val variantGroupNetworks: List<VariantGroupNetwork>
)