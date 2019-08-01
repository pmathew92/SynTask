package com.prince.data.model

import com.squareup.moshi.Json

data class ExclusionsNetwork(
    @Json(name = "group_id")
    val groupId: String,
    @Json(name = "variation_id")
    val variationId: String
)