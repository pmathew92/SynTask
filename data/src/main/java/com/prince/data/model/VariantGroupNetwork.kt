package com.prince.data.model

import com.squareup.moshi.Json

data class VariantGroupNetwork(
    @Json(name = "group_id")
    val groupId: String,
    val name: String,
    @Json(name = "variations")
    val variationNetworks: List<VariationNetwork>,
    var selected: String = ""
)